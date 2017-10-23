package com.hljt.app.domain;

/**
 * Created by Administrator on 2017/10/20.
 */

public class Road {


    /**
     * id : 21
     * unitId : e91e7dbe6bd74210ae951034f98aaff5
     * serverId : 9954953a4c9747d58b2d6240cadc3ab6
     * unitName : 江津大酒店
     * imageName : 单位监控
     * imageType : 1
     * imageDescribe : null
     * url : /group1/M00/00/01/wKjHy1nV0cGAT73UAiWQpG8i-EM442.mp4
     * imageOrder : null
     * timeStart : null
     * timeEnd : null
     * remark : null
     * lon : null
     * lat : null
     * address :
     * defaultDisplay : 1
     * odsFileServer : {"serverId":1,"logicalId":"9954953a4c9747d58b2d6240cadc3ab6","name":"FastDFS虚拟VIP资源访问服务器","os":"ContOS7","ip":"http://192.168.199.203","protocol":null,"username":null,"password":null,"port":"8888","type":"1"}
     */

    private int id;
    private String unitId;
    private String serverId;
    private String unitName;
    private String imageName;
    private String imageType;
    private Object imageDescribe;
    private String url;
    private Object imageOrder;
    private Object timeStart;
    private Object timeEnd;
    private Object remark;
    private double lon;
    private double lat;
    private String address;
    private String defaultDisplay;
    private OdsFileServerBean odsFileServer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getServerId() {
        return serverId;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public Object getImageDescribe() {
        return imageDescribe;
    }

    public void setImageDescribe(Object imageDescribe) {
        this.imageDescribe = imageDescribe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getImageOrder() {
        return imageOrder;
    }

    public void setImageOrder(Object imageOrder) {
        this.imageOrder = imageOrder;
    }

    public Object getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Object timeStart) {
        this.timeStart = timeStart;
    }

    public Object getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Object timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDefaultDisplay() {
        return defaultDisplay;
    }

    public void setDefaultDisplay(String defaultDisplay) {
        this.defaultDisplay = defaultDisplay;
    }

    public OdsFileServerBean getOdsFileServer() {
        return odsFileServer;
    }

    public void setOdsFileServer(OdsFileServerBean odsFileServer) {
        this.odsFileServer = odsFileServer;
    }

    public static class OdsFileServerBean {
        /**
         * serverId : 1
         * logicalId : 9954953a4c9747d58b2d6240cadc3ab6
         * name : FastDFS虚拟VIP资源访问服务器
         * os : ContOS7
         * ip : http://192.168.199.203
         * protocol : null
         * username : null
         * password : null
         * port : 8888
         * type : 1
         */

        private int serverId;
        private String logicalId;
        private String name;
        private String os;
        private String ip;
        private Object protocol;
        private Object username;
        private Object password;
        private String port;
        private String type;

        public int getServerId() {
            return serverId;
        }

        public void setServerId(int serverId) {
            this.serverId = serverId;
        }

        public String getLogicalId() {
            return logicalId;
        }

        public void setLogicalId(String logicalId) {
            this.logicalId = logicalId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public Object getProtocol() {
            return protocol;
        }

        public void setProtocol(Object protocol) {
            this.protocol = protocol;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
