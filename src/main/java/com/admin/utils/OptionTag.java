package com.admin.utils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-03-01
 * Time: 16:03
 * To change this template use File | Settings | File Templates.
 */
public class OptionTag extends SimpleTagSupport {

    private String pageSize;

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();


        switch (pageSize) {
            case "1":
                out.print("<option selected>1</option>");
                out.print("<option>2</option>");
                out.print("<option>3</option>");
                out.print("<option>4</option>");
                out.print("<option>5</option>");
                out.print("<option>6</option>");
                out.print("<option>7</option>");
                out.print("<option>8</option>");
                break;
            case "2":
                out.print("<option>1</option>");
                out.print("<option selected>2</option>");
                out.print("<option>3</option>");
                out.print("<option>4</option>");
                out.print("<option>5</option>");
                out.print("<option>6</option>");
                out.print("<option>7</option>");
                out.print("<option>8</option>");
                break;
            case "3":
                out.print("<option>1</option>");
                out.print("<option>2</option>");
                out.print("<option selected>3</option>");
                out.print("<option>4</option>");
                out.print("<option>5</option>");
                out.print("<option>6</option>");
                out.print("<option>7</option>");
                out.print("<option>8</option>");
                break;
            case "4":
                out.print("<option>1</option>");
                out.print("<option>2</option>");
                out.print("<option>3</option>");
                out.print("<option selected>4</option>");
                out.print("<option>5</option>");
                out.print("<option>6</option>");
                out.print("<option>7</option>");
                out.print("<option>8</option>");
                break;
            case "5":
                out.print("<option>1</option>");
                out.print("<option>2</option>");
                out.print("<option>3</option>");
                out.print("<option>4</option>");
                out.print("<option selected>5</option>");
                out.print("<option>6</option>");
                out.print("<option>7</option>");
                out.print("<option>8</option>");
                break;
            case "6":
                out.print("<option>1</option>");
                out.print("<option>2</option>");
                out.print("<option>3</option>");
                out.print("<option>4</option>");
                out.print("<option>5</option>");
                out.print("<option selected>6</option>");
                out.print("<option>7</option>");
                out.print("<option>8</option>");
                break;
            case "7":
                out.print("<option>1</option>");
                out.print("<option>2</option>");
                out.print("<option>3</option>");
                out.print("<option>4</option>");
                out.print("<option>5</option>");
                out.print("<option>6</option>");
                out.print("<option selected>7</option>");
                out.print("<option>8</option>");
                break;
            default:
                out.print("<option>1</option>");
                out.print("<option>2</option>");
                out.print("<option>3</option>");
                out.print("<option>4</option>");
                out.print("<option>5</option>");
                out.print("<option>6</option>");
                out.print("<option>7</option>");
                out.print("<option selected>8</option>");
                break;
        }


    }
}
