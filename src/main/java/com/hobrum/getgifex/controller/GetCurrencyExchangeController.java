package com.hobrum.getgifex.controller;

import com.hobrum.getgifex.service.GiphyService;
import com.hobrum.getgifex.service.OpenEchangeRatesService;
import feign.FeignException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SpringBootTest
@RestController
public class GetCurrencyExchangeController {

    @MockBean
    @Value("${openExcRatesApiToken}")
    private String openExcRatesApiToken;

    @MockBean
    @Value("${openExNeedCurrencyCode}")
    private String openExNeedCurrencyCode;

    @MockBean
    private OpenEchangeRatesService openEchangeRatesService;

    @MockBean
    private GiphyService giphyService;


    public GetCurrencyExchangeController(OpenEchangeRatesService openEchangeRatesService, GiphyService giphyService) {

        this.openEchangeRatesService = openEchangeRatesService;
        this.giphyService = giphyService;

    }

    @Test
    @GetMapping("/exchange")
    public ResponseEntity getExchange() {

        try{

            boolean isExchangeRateIsHigher = openEchangeRatesService.isExchangeRateIsHigher(openExNeedCurrencyCode);
            byte[] gifByte = giphyService.getRandomGif(isExchangeRateIsHigher);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_GIF).body(gifByte);

        }
        catch (NullPointerException e){
            return ResponseEntity.badRequest().body("Валюта, указанная в параметре openExNeedCurrencyCode (" +openExNeedCurrencyCode +") не существует. " +
                    "Вы можете использовать следующую валюту:\n" +
                    "1) AED, 2) AFN, 3) ALL, 4) AMD, 5) ANG, 6) AOA, 7) ARS, 8) AUD, 9) AWG, 10) AZN, " +
                    "11) BAM, 12) BBD, 13) BDT, 14) BGN, 15) BHD, 16) BIF, 17) BMD, 18) BND, 19) BOB, " +
                    "20) BRL, 21) BSD, 22) BTC, 23) BTN, 24) BWP, 25) BYN, 26) BZD, 27) CAD, 28) CDF, 29) CHF, " +
                    "30) CLF, 31) CLP, 32) CNH, 33) CNY, 34) COP, 35) CRC, 36) CUC, 37) CUP, 38) CVE, 39) CZK, " +
                    "40) DJF, 41) DKK, 42) DOP, 43) DZD, 44) EGP, 45) ERN, 46) ETB, 47) EUR, 48) FJD, 49) FKP, " +
                    "50) GBP, 51) GEL, 52) GGP, 53) GHS, 54) GIP, 55) GMD, 56) GNF, 57) GTQ, 58) GYD, 59) HKD, " +
                    "60) HNL, 61) HRK, 62) HTG, 63) HUF, 64) IDR, 65) ILS, 66) IMP, 67) INR, 68) IQD, 69) IRR, " +
                    "70) ISK, 71) JEP, 72) JMD, 73) JOD, 74) JPY, 75) KES, 76) KGS, 77) KHR, 78) KMF, 79) KPW, " +
                    "80) KRW, 81) KWD, 82) KYD, 83) KZT, 84) LAK, 85) LBP, 86) LKR, 87) LRD, 88) LSL, 89) LYD, " +
                    "90) MAD, 91) MDL, 92) MGA, 93) MKD, 94) MMK, 95) MNT, 96) MOP, 97) MRO, 98) MRU, 99) MUR, " +
                    "100) MVR, 101) MWK, 102) MXN, 103) MYR, 104) MZN, 105) NAD, 106) NGN, 107) NIO, 108) NOK, 109) NPR, " +
                    "110) NZD, 111) OMR, 112) PAB, 113) PEN, 114) PGK, 115) PHP, 116) PKR, 117) PLN, 118) PYG, 119) QAR, " +
                    "120) RON, 121) RSD, 122) RUB, 123) RWF, 124) SAR, 125) SBD, 126) SCR, 127) SDG, 128) SEK, 129) SGD, " +
                    "130) SHP, 131) SLL, 132) SOS, 133) SRD, 134) SSP, 135) STD, 136) STN, 137) SVC, 138) SYP, 139) SZL, " +
                    "140) THB, 141) TJS, 142) TMT, 143) TND, 144) TOP, 145) TRY, 146) TTD, 147) TWD, 148) TZS, 149) UAH, " +
                    "150) UGX, 151) USD, 152) UYU, 153) UZS, 154) VES, 155) VND, 156) VUV, 157) WST, 158) XAF, 159) XAG, " +
                    "160) XAU, 161) XCD, 162) XDR, 163) XOF, 164) XPD, 165) XPF, 166) XPT, 167) YER, 168) ZAR, 169) ZMW, " +
                    "170) ZWL.");
        }
        catch (FeignException.Forbidden e) {
            return ResponseEntity.badRequest().body("В данный момент нельзя использовать валюту отличную от USD, измените значение в" +
                    " параметре openExBaseCurrencyCode на USD");
        }
        catch (IOException e){
            return ResponseEntity.badRequest().body("Неизвестная ошибка");
        }

    }

}
