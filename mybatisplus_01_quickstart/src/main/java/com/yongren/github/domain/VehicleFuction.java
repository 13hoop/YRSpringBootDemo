package com.yongren.github.domain;

public class VehicleFuction {
    String name;
    int code;
    String relateIds;

    public VehicleFuction(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRelateIds() {
        return relateIds;
    }

    public void setRelateIds(String relateIds) {
        this.relateIds = relateIds;
    }

    @Override
    public String toString() {
        return "VehicleFuction{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", relateIds='" + relateIds + '\'' +
                '}';
    }
}
