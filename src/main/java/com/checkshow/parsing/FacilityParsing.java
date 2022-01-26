package com.checkshow.parsing;

import com.checkshow.dto.request.FacilityRequest;
import com.checkshow.model.FacilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FacilityParsing extends ParsingConfig {

    private FacilityService facilityService;

    /**
     * @param str : 문자열
     * @return Short
     *
     * 문자열을 매개변수로 받아 Short형으로 반환하는 메소드
     * 파싱할 때 exception 오류가나서 따로 함수를 만듬
     */
    public Short parseShort(String str) {
        short result;

        try {
            result = Short.parseShort(str);
        } catch (Exception e) {
            return null;
        }

        return result;
    }

    /**
     * @param id : String형 Facility id(mt10id)
     * @return FacilityRequest
     *
     * Facility id를 매개변수로 받아 api를 호출하여 Facility의 정보들을 파싱해서
     * DTO에 데이터를 집어넣어 반환하는 함수
     */
    public FacilityRequest loadDataByFacilityId(String id) {
        FacilityRequest dto = null;
        String url = "http://www.kopis.or.kr/openApi/restful/prfplc/" + id + "?service=" + getApiKey();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(url);

            doc.getDocumentElement().normalize();
            Node node = doc.getElementsByTagName("db").item(0);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                dto = FacilityRequest.builder()
                        .id(getTagValue("mt10id", element))
                        .facilityCount(Short.parseShort(Objects.requireNonNull(getTagValue("mt13cnt", element))))
                        .facilityCharacteristic(getTagValue("fcltychartr", element))
                        .facilityName(getTagValue("fcltynm", element))
                        .yearOpen(parseShort(getTagValue("opende", element)))
                        .seatScale(Integer.parseInt(Objects.requireNonNull(getTagValue("seatscale", element))))
                        .telNumber(getTagValue("telno", element))
                        .relateUrl(getTagValue("relateurl", element))
                        .address(getTagValue("adres", element))
                        .latitude(Double.parseDouble(Objects.requireNonNull(getTagValue("la", element))))
                        .longitude(Double.parseDouble(Objects.requireNonNull(getTagValue("lo", element))))
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dto;
    }
}
