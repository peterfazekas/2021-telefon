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

    /**
     * 3. feladat
     */
    public String getCallStatisticByHour() {
        return callBeginCountMap().entrySet().stream()
                .map(i -> String.format("%d óra %d hívás", i.getKey(), i.getValue()))
                .collect(Collectors.joining("\n"));
    }

    private Map<Integer, Long> callBeginCountMap() {
        return callLogs.stream()
                .collect(Collectors.groupingBy(CallLog::getCallHour, TreeMap::new, Collectors.counting()));
    }

    /**
     * 4. feladat
     */
    public String getLongestCallDetails() {
        CallLog longestCall = getLongestCall();
        return String.format("A leghosszabb ideig vonalban lévő hívó %d. sorban szerepel, "
                + "a hívás hossza: %d másodperc.", longestCall.getId(), longestCall.getCallLength());
    }

    private CallLog getLongestCall() {
        return callLogs.stream()
                .max(Comparator.comparing(CallLog::getCallLength))
                .get();

    }

    /**
     * 5. feladat
     */
    public String getActualCallerDetails(LogTime time) {
        return getActualCallerId(time)
                .map(i -> String.format("A várakozók száma: %d a beszélő a %d. hívó.",
                        getWaitingCallerCount(time), i))
                .orElse("Nem volt beszélő.");
    }

    private Long getWaitingCallerCount(LogTime time) {
        return callLogs.stream()
                .filter(i -> i.isWaiting(time))
                .count();
    }

    private Optional<Integer> getActualCallerId(LogTime time) {
        return callLogs.stream()
                .filter(i -> i.isSpeaking(time))
                .map(CallLog::getId)
                .findFirst();
    }
}
