package com.codinginflow.bigots;

import java.util.HashMap;
import java.util.Map;

public class CoinMapper {
    private static final Map<String, String> binanceMapping = new HashMap<>();
    private static final Map<String, String> paribuMapping = new HashMap<>();
    private static final Map<String, String> paribuTradeMapping = new HashMap<>();
    private static final Map<String, String> paribuWalletMapping = new HashMap<>();

    static {
        // Binance mappings
        binanceMapping.put("MIOTA", "IOTA");
        binanceMapping.put("RNDR", "RENDER");
        binanceMapping.put("MATIC", "POL");
        binanceMapping.put("BEAM", "BEAMX");

    }

    static {
        // Paribu mappings
        paribuMapping.put("IOTA", "MIOTA");
        paribuMapping.put("RENDER", "RNDR");
        paribuMapping.put("POL", "MATIC");
        binanceMapping.put("BEAMX", "BEAM");

    }

    static {
        // Sadece özel durumlar için trade mapping
        paribuTradeMapping.put("SEI_TL", "sei_tl");
        paribuTradeMapping.put("API3_TL", "api3_tl");
        paribuTradeMapping.put("MINA_TL", "mina_tl");
        paribuTradeMapping.put("ETHFI_TL", "ethfi_tl");
        paribuTradeMapping.put("EIGEN_TL", "eigen_tl");
        paribuTradeMapping.put("1INCH_TL", "1inch_tl");
        paribuTradeMapping.put("MATIC_TL", "matic_tl");
        paribuTradeMapping.put("SUI_TL", "sui_tl");
        paribuTradeMapping.put("FLOKI_TL", "floki_tl");
        paribuTradeMapping.put("MIOTA_TL", "miota_tl");
        paribuTradeMapping.put("IO_TL", "io_tl");
        paribuTradeMapping.put("ICP_TL", "icp_tl");
        paribuTradeMapping.put("INJ_TL", "inj_tl");
        paribuTradeMapping.put("IMX_TL", "imx_tl");
        paribuTradeMapping.put("TIA_TL", "tia_tl");
        paribuTradeMapping.put("ALICE_TL", "alice_tl");
        paribuTradeMapping.put("CITY_TL", "city_tl");
        paribuTradeMapping.put("FIL_TL", "fil_tl");
    }
    static {
        // Wallet mappings - Büyük I olan tüm coinler için
        paribuWalletMapping.put("SEI", "sei");
        paribuWalletMapping.put("API3", "api3");
        paribuWalletMapping.put("MINA", "mina");
        paribuWalletMapping.put("ETHFI", "ethfi");
        paribuWalletMapping.put("EIGEN", "eigen");
        paribuWalletMapping.put("1INCH", "1inch");
        paribuWalletMapping.put("MATIC", "matic");
        paribuWalletMapping.put("SUI", "sui");
        paribuWalletMapping.put("FLOKI", "floki");
        paribuWalletMapping.put("MIOTA", "miota");
        paribuWalletMapping.put("IO", "io");
        paribuWalletMapping.put("ICP", "icp");
        paribuWalletMapping.put("INJ", "nmj");
        paribuWalletMapping.put("IMX", "imx");
        paribuWalletMapping.put("TIA", "tia");
        paribuWalletMapping.put("ALICE", "alice");
        paribuWalletMapping.put("CITY", "city");
    }
    public static String getBinanceSymbol(String paribuSymbol) {
        String baseSymbol = paribuSymbol.replace("_TL", "").toUpperCase();
        String mappedSymbol = binanceMapping.get(baseSymbol);
        return mappedSymbol != null ? mappedSymbol : baseSymbol;
    }

    public static String getParibTradeUrl(String coinName) {
        // Trade URL'si için mapping kontrolü
        String tradeMapping = paribuTradeMapping.get(coinName.toUpperCase());
        if (tradeMapping != null) {
            return tradeMapping;
        }

        // Normal durumda her şey küçük harf
        String baseSymbol = coinName.replace("_TL", "").toUpperCase();
        String mappedSymbol = paribuMapping.get(baseSymbol);
        return (mappedSymbol != null ? mappedSymbol : baseSymbol).toLowerCase() + "_tl";
    }

    public static String getParibWalletUrl(String coinName) {
        // Wallet URL'si için mapping kontrolü
        String baseSymbol = coinName.replace("_TL", "").toUpperCase();
        String walletMapping = paribuWalletMapping.get(baseSymbol);

        if (walletMapping != null) {
            return walletMapping;
        }

        // Normal durumda küçük harf
        return baseSymbol.toLowerCase();
    }
}