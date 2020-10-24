package com.webscrapper.webcrawling;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;
public class PipelineHolder {
    public static enum MODE {
        SITE,
        ELEMENT
    }

    private String siteId;
    private MODE mode;
    private String url;
    private JSONObject siteConfig;
    private List<String> elementUrls = Collections.emptyList();
    private String structureFile = "/structure.json";
    private List<JSONObject> elements = new ArrayList<>();
    private String tempFolder;

    public PipelineHolder(String siteId) {
        this.siteId = siteId;
    }

    public PipelineHolder setSiteCrawl(String siteUrl) {
        this.mode = MODE.SITE;
        this.url = siteUrl;
        return this;
    }

    public PipelineHolder setElementCrawl(String elementUrl) {
        this.mode = MODE.ELEMENT;
        this.url = elementUrl;
        return this;
    }

    public void setSiteConfig(JSONObject siteConfig) {
        this.siteConfig = siteConfig;
        if (this.url == null) {
            setSiteCrawl(siteConfig.getString("site_url"));
        }
    }

    public JSONObject getSiteConfig() {
        return siteConfig;
    }

    public String getSiteId() {
        return siteId;
    }

    private MODE getMode() {
        return mode;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getElementUrls() {
        return elementUrls;
    }

    public void setElementUrls(List<String> elementUrls) {
        this.elementUrls = elementUrls;
    }

    public boolean isASiteCrawl() {
        return MODE.SITE.equals(mode);
    }

    public boolean isAnElementCrawl() {
        return MODE.ELEMENT.equals(mode);
    }

    public JSONObject getElementUrlsConfig() {
        return getSiteConfig().has("element_urls") ? getSiteConfig().getJSONObject("element_urls") : null;
    }

    public String getStructureFile() {
        return structureFile;
    }

    public void setStructureFile(String structureFile) {
        this.structureFile = structureFile;
    }

    public void addElements(List<JSONObject> newElements) {
        elements.addAll(newElements);
    }

    public List<JSONObject> getElements() {
        return elements;
    }

    public String getTempFile() {
        return tempFolder + siteId + ".html";
    }

    public String getTempFileUrl() {
        return "file:///" + getTempFile();
    }

    public void setTempFolder(String tempFolder) {
        this.tempFolder = tempFolder;
    }
}


