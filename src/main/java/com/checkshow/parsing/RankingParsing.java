package com.checkshow.parsing;

import com.checkshow.dto.request.PerformanceRequest;
import com.checkshow.dto.request.RankingRequest;
import com.checkshow.entity.Genre;
import com.checkshow.entity.Performance;
import com.checkshow.entity.constant.GenreEnum;
import com.checkshow.model.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RankingParsing extends ParsingConfig {

    private final RankingService rankingService;

    private final PerformanceParsing performanceParsing;

    public List<RankingRequest> findRankingAllByGenreAndGuCode(Genre genre, String guCode) {
        List<RankingRequest> list = new ArrayList<>();
        GenreEnum genreEnum = genre.toEnum();

        String url = "http://kopis.or.kr/openApi/restful/boxoffice?service=" +
                getApiKey() +
                "&ststype=day&date=" +
                getYesterday() +
                "&catecode=" +
                genreEnum.getCode() +
                "&area=" +
                guCode;

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("boxof");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    Map<String, Object> map = performanceParsing.loadDataByPerformanceId(getTagValue("mt20id", element));
                    Performance performance = ((PerformanceRequest) map.get("PerformanceRequest")).toEntity();
                    performanceParsing.saveDataByPerformanceMap(map);

                    RankingRequest rankingRequest = RankingRequest.builder()
                            .performance(performance)
                            .genre(genre)
                            .guCode(guCode)
                            .rankNumber(Byte.parseByte(Objects.requireNonNull(getTagValue("rnum", element))))
                            .build();

                    list.add(rankingRequest);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
