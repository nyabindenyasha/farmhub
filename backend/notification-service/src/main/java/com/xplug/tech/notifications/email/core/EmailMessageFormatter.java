package com.xplug.tech.notifications.email.core;

import java.util.*;



public class EmailMessageFormatter {

    private static final String PARAGRAPH_BREAK = "<br /><br />";
    private static final String LINE_BREAK = "<br />";
    private String greeting;
    private List<String> paragraphs;
    private String salutation;
    private Collection<Link> links;

    public EmailMessageFormatter() {
    }

    public static String boldText(String text) {
        return "<strong>" + text + "</strong>";
    }

    public String buildMessage() {
        StringBuilder bodyBuilder = new StringBuilder();
        bodyBuilder.append(Objects.isNull(this.greeting) ? "Hi " : this.greeting);
        bodyBuilder.append("<br /><br />");
        this.paragraphs.forEach((paragraph) -> {
            bodyBuilder.append(paragraph);
            bodyBuilder.append("<br /><br />");
        });
        if (Objects.nonNull(this.links) && !this.links.isEmpty()) {
            bodyBuilder.append(boldText("Please click on the following links for : "));
            bodyBuilder.append("<br />");
            this.links.forEach((link) -> {
                bodyBuilder.append(this.getLinkText(link));
                bodyBuilder.append("<br />");
            });
            bodyBuilder.append("<br /><br />");
        }

        bodyBuilder.append(Objects.isNull(this.salutation) ? "Regards," : this.salutation);
        return bodyBuilder.toString();
    }

    void clearFields() {
        if (Objects.nonNull(this.links)) {
            this.links.clear();
        }

        if (Objects.nonNull(this.paragraphs)) {
            this.paragraphs.clear();
        }

    }

    private String getLinkText(Link link) {
        String var10000 = link.getValue();
        return boldText("<a href=" + var10000 + ">" + link.getTitle() + "</a>");
    }

    public void addGreeting(String greeting) {
        this.greeting = greeting;
    }

    public void addParagraph(String paragraph) {
        if (Objects.isNull(this.paragraphs)) {
            this.paragraphs = new ArrayList();
        }

        this.paragraphs.add(paragraph);
    }

    public void addPleaseLogInForDetails() {
        this.paragraphs.add("Please log in for more details.");
    }

    public void addWelcomeToTheFamily() {
        this.paragraphs.add("Welcome to the Bonvie family.");
    }

    public void addSalutation(String salutation) {
        this.salutation = salutation;
    }

    public void addLink(Link link) {
        if (Objects.isNull(this.links)) {
            this.links = new HashSet();
        }

        this.links.add(link);
    }

    public void addTabularHierarchy(String tabularHeading, Map<String, String> content) {
        this.paragraphs.add(boldText(tabularHeading));
        StringBuilder builder = new StringBuilder();
        content.forEach((key, value) -> {
            builder.append(boldText(key)).append(" : ").append(value).append("<br />");
        });
        this.paragraphs.add(builder.toString());
    }

    public static class Link {
        private String title;
        private String value;

        public Link(String title, String value) {
            this.title = title;
            this.value = value;
        }

        private Link() {
        }

        public static LinkBuilder builder() {
            return new LinkBuilder();
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getValue() {
            return this.value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String toString() {
            String var10000 = this.getTitle();
            return "EmailMessageFormatter.Link(title=" + var10000 + ", value=" + this.getValue() + ")";
        }

        public static class LinkBuilder {
            private String title;
            private String value;

            LinkBuilder() {
            }

            public LinkBuilder title(String title) {
                this.title = title;
                return this;
            }

            public LinkBuilder value(String value) {
                this.value = value;
                return this;
            }

            public Link build() {
                return new Link(this.title, this.value);
            }

            public String toString() {
                return "EmailMessageFormatter.Link.LinkBuilder(title=" + this.title + ", value=" + this.value + ")";
            }
        }
    }

}
