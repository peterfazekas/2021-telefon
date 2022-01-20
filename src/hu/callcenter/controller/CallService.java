package hu.callcenter.controller;

import hu.callcenter.domain.model.CallLog;
import hu.callcenter.domain.model.LogTime;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CallService {

    private final List<CallLog> callLogs;

    public CallService(List<CallLog> callLogs) {
        this.callLogs = callLogs;
    }

}
