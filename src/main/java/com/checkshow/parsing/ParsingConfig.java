package com.checkshow.parsing;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


// 파싱 서비스에 공통으로 사용되는 메소드, 변수
@Getter
public abstract class ParsingConfig {

    // kopis 호출 api키
    @Value("${kopis_api_key}")
    private String apiKey;

    // 오늘, 내일 날짜 BASIC_ISO_DATE 형태인 String
    private final String yesterday = LocalDate.now().minusDays(1).format(DateTimeFormatter.BASIC_ISO_DATE);
    private final String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);

    /**
     * @param tag(String)
     * @param element(Element)
     * @return value(String)
     *
     * Element와 String을 매개변수로 받아 해당 노드의 데이터를 반환하는 메소드
     */
    public static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nodeList.item(0);

        if (nValue == null) {
            return null;
        }

        return nValue.getNodeValue();
    }
}
