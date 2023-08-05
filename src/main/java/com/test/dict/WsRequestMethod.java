package com.test.dict;

public enum WsRequestMethod {
    HEDGE_SPOT_TRADE_SUBSCRIBE,
    HEDGE_SPOT_ORDER,
    HEDGE_SPOT_ORDER_ACK,
    ;

    public static WsRequestMethod safeParseMethod(String method) {
        WsRequestMethod wsRequestMethod;
        try {
            wsRequestMethod = WsRequestMethod.valueOf(method);
        } catch (Exception exception) {
            return null;
        }
        return wsRequestMethod;
    }

    public static void main(String[] args) {
        System.out.println(WsRequestMethod.safeParseMethod(null));
    }

}