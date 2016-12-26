package com.exam.web.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by NeilHY on 2016/12/26.
 */
public class SetPaperTimeRequest {
    private Long paper_id;
    private String paper_start;
    private String paper_end;

    public SetPaperTimeRequest() {
    }

    public SetPaperTimeRequest(Long paper_id, String paper_start, String paper_end) {
        this.paper_id = paper_id;
        this.paper_start = paper_start;
        this.paper_end = paper_end;
    }

    public Long getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(Long paper_id) {
        this.paper_id = paper_id;
    }

    public String getPaper_start() {
        return paper_start;
    }

    public void setPaper_start(String paper_start) {
        this.paper_start = paper_start;
    }

    public String getPaper_end() {
        return paper_end;
    }

    public void setPaper_end(String paper_end) {
        this.paper_end = paper_end;
    }
}
