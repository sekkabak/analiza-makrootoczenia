package raport;

import java.util.ArrayList;

public class Table {
    StringBuilder html = new StringBuilder();

    public String getContent() {
        return "<table>" +
                html.toString() +
                "</table>";
    }

    public void addHeader(ArrayList<String> cells) {
        html.append("<tr>");
        for (String x : cells) {
            html.append("<th>").append(x).append("</th>");
        }
        html.append("</tr>");
    }

    public void addRow(ArrayList<String> cells) {
        html.append("<tr>");
        for (String x : cells) {
            html.append("<td>").append(x).append("</td>");
        }
        html.append("</tr>");
    }

    public void addHeader(String str, int colspan) {
        html
                .append("<tr>")
                .append("<td class='span' colspan='")
                .append(colspan)
                .append("'>")
                .append(str)
                .append("</td>")
                .append("</tr>");
    }
}
