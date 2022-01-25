package com.checkshow.parsing;

import com.checkshow.dto.request.FacilityRequest;
import com.checkshow.dto.request.IntroImageRequest;
import com.checkshow.dto.request.PerformanceRequest;
import com.checkshow.entity.constant.GenreEnum;
import com.checkshow.entity.constant.StateEnum;
import com.checkshow.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PerformanceParsing extends ParsingConfig {

    private final FacilityService facilityService;
    private final PerformanceService performanceService;
    private final IntroImageService introImageService;
    private final GenreService genreService;
    private final StateService stateService;
    private final FacilityParsing facilityParsing;

    // 문자열인 형태를 바이트로 바꿔주는 메소드(age 컬럼에 형을 맞춰야함)
    public Byte getAge(String str) {
        byte result;

        if (str.contains("전체")) {
            result = 0;
        } else {
            try {
                result = Byte.parseByte(str.replaceAll("[^\\d]", ""));
            } catch (Exception e) {
                result = 0;
            }
            if (str.contains("개월")) {
                result /= 12;
            }
        }

        return result;
    }

    // 오늘 날짜를 기준으로 공연 아이디 목록 불러오는 메소드
    public List<String> loadAllPerformanceIdToday(){
        List<String> list = new ArrayList<>();

        String url = "http://www.kopis.or.kr/openApi/restful/pblprfr?service="
                + getApiKey()
                + "&stdate="
                + getYesterday()
                + "&eddate="
                + getToday()
                + "&cpage=1&rows=1000";

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("db");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    list.add(getTagValue("mt20id", element));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // id를 다시 api로 호출해 값들을 map에 저장해서 반환하는 메소드
    public Map<String, Object> loadDataByPerformanceId(String performanceId) {
        Map<String, Object> map = new HashMap<>();

        String url = "http://www.kopis.or.kr/openApi/restful/pblprfr/" + performanceId + "?service=" + getApiKey();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            doc.getDocumentElement().normalize();
            Node node = doc.getElementsByTagName("db").item(0);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                // 데이터 map에 저장하기(총 19개)

                // 문자열을 LocalDate로 포맷팅하여 저장;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");

                // Genre와 State는 enum타입으로 데이터 찾아서 저장
                GenreEnum genreEnum = GenreEnum.findByComment(getTagValue("genrenm", element));
                StateEnum stateEnum = StateEnum.findByComment(getTagValue("prfstate", element));

                // Performace 엔티티에 Facility가 존재해야 매핑할 수 있어서 미리 저장.
                FacilityRequest facilityRequest = facilityParsing.loadDataByFacilityId(getTagValue("mt10id", element));

                PerformanceRequest performanceRequest = PerformanceRequest.builder()
                        .id(getTagValue("mt20id", element))
                        .performanceName(getTagValue("prfnm", element))
                        .performanceTime(getTagValue("dtguidance", element))
                        .facilityDetailName(getTagValue("fcltynm", element))
                        .cast(getTagValue("prfcast", element))
                        .crew(getTagValue("prfcrew", element))
                        .runtime(getTagValue("prfruntime", element))
                        .age(getAge(Objects.requireNonNull(getTagValue("prfage", element))))
                        .productionCompany(getTagValue("entrpsnm", element))
                        .price(getTagValue("pcseguidance", element))
                        .posterUrl(getTagValue("poster", element))
                        .story(getTagValue("sty", element))
                        .openRun(Objects.equals(getTagValue("openrun", element), "Y"))
                        .startDate(LocalDate.parse(Objects.requireNonNull(getTagValue("prfpdfrom", element)), formatter))
                        .endDate(LocalDate.parse(Objects.requireNonNull(getTagValue("prfpdto", element)), formatter))
                        .genre(genreEnum.toEntity(genreService))
                        .state(stateEnum.toEntity(stateService))
                        .facility(facilityRequest.toEntity())
                        .build();

                // 이미지 url들 리스트로 저장후 맵에 넣기
                NodeList styleUrls = element.getElementsByTagName("styurls");
                List<IntroImageRequest> introImageRequests = new ArrayList<>();
                for (int j = 0; j < styleUrls.getLength(); j++) {
                    Node urlNode = styleUrls.item(j);
                    if (urlNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element element1 = (Element) urlNode;

                        introImageRequests.add(
                                IntroImageRequest.builder()
                                        .id(String.format("%s_%d", performanceId, (j + 1)))
                                        .performance(performanceRequest.toEntity())
                                        .url(getTagValue("styurl", element1))
                                        .build()
                        );
                    }
                }

                map.put("FacilityRequest", facilityRequest);
                map.put("PerformanceRequest", performanceRequest);
                map.put("IntroImageRequests", introImageRequests);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    // performanceId를 이용해 데이터를 불러와서 DB에 저장.
    public String saveDataByPerformanceId(String performanceId) {
        Map<String, Object> map = loadDataByPerformanceId(performanceId);

        facilityService.save((FacilityRequest) map.get("FacilityRequest"));
        performanceService.save((PerformanceRequest) map.get("PerformanceRequest"));
        introImageService.deleteAllByPerformance(((PerformanceRequest) map.get("PerformanceRequest")).toEntity());
        introImageService.saveAll((List<IntroImageRequest>) map.get("IntroImageRequests"));

        return performanceId;
    }

    // loadDataByPerformanceId 함수로 반환된 map을 이용해 DTO들 저장하는 메소드
    public void saveDataByPerformanceMap(Map<String, Object> map) {
        facilityService.save((FacilityRequest) map.get("FacilityRequest"));
        performanceService.save((PerformanceRequest) map.get("PerformanceRequest"));
        introImageService.deleteAllByPerformance(((PerformanceRequest) map.get("PerformanceRequest")).toEntity());
        introImageService.saveAll((List<IntroImageRequest>) map.get("IntroImageRequests"));
    }
}
