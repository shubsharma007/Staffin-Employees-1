package com.example.staffinemployees.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EventsByYearResponse {
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("event_details")
    @Expose
    private EventDetails eventDetails;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    public class EventDetails {

        @SerializedName("January")
        @Expose
        private List<January> january;
        @SerializedName("February")
        @Expose
        private List<February> february;
        @SerializedName("March")
        @Expose
        private List<March> march;
        @SerializedName("April")
        @Expose
        private List<April> april;
        @SerializedName("May")
        @Expose
        private List<May> may;
        @SerializedName("June")
        @Expose
        private List<June> june;
        @SerializedName("July")
        @Expose
        private List<July> july;
        @SerializedName("August")
        @Expose
        private List<August> august;
        @SerializedName("September")
        @Expose
        private List<September> september;
        @SerializedName("October")
        @Expose
        private List<October> october;
        @SerializedName("November")
        @Expose
        private List<November> november;
        @SerializedName("December")
        @Expose
        private List<December> december;

        public List<January> getJanuary() {
            return january;
        }

        public void setJanuary(List<January> january) {
            this.january = january;
        }

        public List<February> getFebruary() {
            return february;
        }

        public void setFebruary(List<February> february) {
            this.february = february;
        }

        public List<March> getMarch() {
            return march;
        }

        public void setMarch(List<March> march) {
            this.march = march;
        }

        public List<April> getApril() {
            return april;
        }

        public void setApril(List<April> april) {
            this.april = april;
        }

        public List<May> getMay() {
            return may;
        }

        public void setMay(List<May> may) {
            this.may = may;
        }

        public List<June> getJune() {
            return june;
        }

        public void setJune(List<June> june) {
            this.june = june;
        }

        public List<July> getJuly() {
            return july;
        }

        public void setJuly(List<July> july) {
            this.july = july;
        }

        public List<August> getAugust() {
            return august;
        }

        public void setAugust(List<August> august) {
            this.august = august;
        }

        public List<September> getSeptember() {
            return september;
        }

        public void setSeptember(List<September> september) {
            this.september = september;
        }

        public List<October> getOctober() {
            return october;
        }

        public void setOctober(List<October> october) {
            this.october = october;
        }

        public List<November> getNovember() {
            return november;
        }

        public void setNovember(List<November> november) {
            this.november = november;
        }

        public List<December> getDecember() {
            return december;
        }

        public void setDecember(List<December> december) {
            this.december = december;
        }

        public class January {
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title_name")
            @Expose
            private String titleName;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("img1")
            @Expose
            private String img1;
            @SerializedName("img2")
            @Expose
            private String img2;
            @SerializedName("img3")
            @Expose
            private String img3;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("add_member")
            @Expose
            private String addMember;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAddMember() {
                return addMember;
            }

            public void setAddMember(String addMember) {
                this.addMember = addMember;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }

        public class February {
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title_name")
            @Expose
            private String titleName;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("img1")
            @Expose
            private String img1;
            @SerializedName("img2")
            @Expose
            private String img2;
            @SerializedName("img3")
            @Expose
            private String img3;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("add_member")
            @Expose
            private String addMember;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAddMember() {
                return addMember;
            }

            public void setAddMember(String addMember) {
                this.addMember = addMember;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }

        public class March {
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title_name")
            @Expose
            private String titleName;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("img1")
            @Expose
            private String img1;
            @SerializedName("img2")
            @Expose
            private String img2;
            @SerializedName("img3")
            @Expose
            private String img3;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("add_member")
            @Expose
            private String addMember;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAddMember() {
                return addMember;
            }

            public void setAddMember(String addMember) {
                this.addMember = addMember;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }

        public class April {
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title_name")
            @Expose
            private String titleName;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("img1")
            @Expose
            private String img1;
            @SerializedName("img2")
            @Expose
            private String img2;
            @SerializedName("img3")
            @Expose
            private String img3;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("add_member")
            @Expose
            private String addMember;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAddMember() {
                return addMember;
            }

            public void setAddMember(String addMember) {
                this.addMember = addMember;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }

        public class May {
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title_name")
            @Expose
            private String titleName;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("img1")
            @Expose
            private String img1;
            @SerializedName("img2")
            @Expose
            private String img2;
            @SerializedName("img3")
            @Expose
            private String img3;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("add_member")
            @Expose
            private String addMember;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAddMember() {
                return addMember;
            }

            public void setAddMember(String addMember) {
                this.addMember = addMember;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }

        public class June {
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title_name")
            @Expose
            private String titleName;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("img1")
            @Expose
            private String img1;
            @SerializedName("img2")
            @Expose
            private String img2;
            @SerializedName("img3")
            @Expose
            private String img3;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("add_member")
            @Expose
            private String addMember;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAddMember() {
                return addMember;
            }

            public void setAddMember(String addMember) {
                this.addMember = addMember;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }

        public class July {
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title_name")
            @Expose
            private String titleName;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("img1")
            @Expose
            private String img1;
            @SerializedName("img2")
            @Expose
            private String img2;
            @SerializedName("img3")
            @Expose
            private String img3;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("add_member")
            @Expose
            private String addMember;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAddMember() {
                return addMember;
            }

            public void setAddMember(String addMember) {
                this.addMember = addMember;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }

        public class August {
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title_name")
            @Expose
            private String titleName;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("img1")
            @Expose
            private String img1;
            @SerializedName("img2")
            @Expose
            private String img2;
            @SerializedName("img3")
            @Expose
            private String img3;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("add_member")
            @Expose
            private String addMember;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAddMember() {
                return addMember;
            }

            public void setAddMember(String addMember) {
                this.addMember = addMember;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }

        public class September {
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title_name")
            @Expose
            private String titleName;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("img1")
            @Expose
            private String img1;
            @SerializedName("img2")
            @Expose
            private String img2;
            @SerializedName("img3")
            @Expose
            private String img3;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("add_member")
            @Expose
            private String addMember;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAddMember() {
                return addMember;
            }

            public void setAddMember(String addMember) {
                this.addMember = addMember;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }

        public class October {
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title_name")
            @Expose
            private String titleName;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("img1")
            @Expose
            private String img1;
            @SerializedName("img2")
            @Expose
            private String img2;
            @SerializedName("img3")
            @Expose
            private String img3;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("add_member")
            @Expose
            private String addMember;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAddMember() {
                return addMember;
            }

            public void setAddMember(String addMember) {
                this.addMember = addMember;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }

        public class November {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title_name")
            @Expose
            private String titleName;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("img1")
            @Expose
            private String img1;
            @SerializedName("img2")
            @Expose
            private String img2;
            @SerializedName("img3")
            @Expose
            private String img3;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("add_member")
            @Expose
            private String addMember;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAddMember() {
                return addMember;
            }

            public void setAddMember(String addMember) {
                this.addMember = addMember;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }

        public class December {
            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("title_name")
            @Expose
            private String titleName;
            @SerializedName("image")
            @Expose
            private String image;
            @SerializedName("img1")
            @Expose
            private String img1;
            @SerializedName("img2")
            @Expose
            private String img2;
            @SerializedName("img3")
            @Expose
            private String img3;
            @SerializedName("location")
            @Expose
            private String location;
            @SerializedName("description")
            @Expose
            private String description;
            @SerializedName("date")
            @Expose
            private String date;
            @SerializedName("add_member")
            @Expose
            private String addMember;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getImg1() {
                return img1;
            }

            public void setImg1(String img1) {
                this.img1 = img1;
            }

            public String getImg2() {
                return img2;
            }

            public void setImg2(String img2) {
                this.img2 = img2;
            }

            public String getImg3() {
                return img3;
            }

            public void setImg3(String img3) {
                this.img3 = img3;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getAddMember() {
                return addMember;
            }

            public void setAddMember(String addMember) {
                this.addMember = addMember;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }

    }
}



