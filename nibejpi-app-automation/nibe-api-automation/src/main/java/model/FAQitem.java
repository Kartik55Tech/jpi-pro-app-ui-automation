package model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class FAQitem {

    @SerializedName("faqId")
    public int faqId;

    @SerializedName("order")
    public int order;

    @SerializedName("text")
    public String text;

    @SerializedName("brand")
    public String brand;

    @SerializedName("tags")
    public List<String> tags;

    @SerializedName("sections")
    public List<Section> sections;

    // Default constructor
    public FAQitem() {
        // Empty constructor required for Jackson deserialization
    }

    // Getters and setters
    public int getFaqId() {
        return faqId;
    }

    public void setFaqId(int faqId) {
        this.faqId = faqId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    @Override
    public String toString() {
        return "FAQitem{" +
                "faqId=" + faqId +
                ", order=" + order +
                ", text='" + text + '\'' +
                ", brand='" + brand + '\'' +
                ", tags=" + tags +
                ", sections=" + sections +
                '}';
    }
    
    public static class Section {

        @SerializedName("order")
        public int order;

        @SerializedName("type")
        public String type;

        @SerializedName("content")
        public String content;

        // Default constructor
        public Section() {
            // Empty constructor required for Jackson deserialization
        }

        // Getters and setters
        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getContent() {
            // Remove HTML tags and their closing tags using regular expression
        	String con=content.replaceAll("<[^>]*>", "").trim() ;
        	System.out.println(con);
            return content.replaceAll("<[^>]*>", "").trim();
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "Section{" +
                    "order=" + order +
                    ", type='" + type + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }
    }
}
