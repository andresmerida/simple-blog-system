package org.adk.sbs.dto;

public record CustomErrorResponse (Integer status, String message, Object data){
}
