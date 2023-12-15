package com.finp.moic.giftCard.application.port.out.external;

import java.util.List;

public interface NaverOcrPort {


    public List<String> naverOcrApi(String filePath, String ext);

    public List<String> jsonParse(StringBuffer response);
}
