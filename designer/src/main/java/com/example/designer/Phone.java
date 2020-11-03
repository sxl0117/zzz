package com.example.designer;

public class Phone {

    private String lanya;

    private String wifi;

    private String shexiangtou;

    private String cpu;

    private String pingmu;

    public Phone() {
        this(new Builder());
    }

    private Phone(Builder builder) {
        this.cpu = builder.cpu;
        this.lanya = builder.lanya;
        this.pingmu = builder.pingmu;
        this.wifi = builder.wifi;
        this.shexiangtou = builder.shexiangtou;
    }

    public static class Builder {
        private String lanya;

        private String wifi;

        private String shexiangtou;

        private String cpu;

        private String pingmu;

        public Builder setLanya(String lanya) {
            this.lanya = lanya;
            return this;
        }

        public Builder setWifi(String wifi) {
            this.wifi = wifi;
            return this;
        }

        public Builder setShexiangtou(String shexiangtou) {
            this.shexiangtou = shexiangtou;
            return this;
        }

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setPingmu(String pingmu) {
            this.pingmu = pingmu;
            return this;
        }
        public Phone build(){
            return new Phone(this);
        }
    }
}
