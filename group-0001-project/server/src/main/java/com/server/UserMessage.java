package com.server;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class UserMessage {

    // the needed attributes to an user message
    private String locationName;
    private String locationDescription;
    private String locationCity;
    private String locationCountry;
    private String locationStreetAddress;
    private String originalPoster;
    private ZonedDateTime originalPostingTime;
    private ZonedDateTime sent;
    private String latitude;
    private String longitude;

    /**
     * Constructs a UserMessage object with provided attributes.
     * 
     * @param locationName          The name of the location associated with the
     *                              message.
     * @param locationDescription   The description of the location.
     * @param locationCity          The city of the location.
     * @param locationCountry       The country of the location.
     * @param locationStreetAddress The street address of the location.
     * @param originalPoster        The original poster of the message.
     * @param postingTime           The posting time of the message in format
     *                              "yyyy-MM-dd'T'HH:mm:ss.SSSX".
     * @param latitude              The latitude coordinate of the location.
     * @param longitude             The longitude coordinate of the location.
     */
    public UserMessage(String locationName, String locationDescription, String locationCity, String locationCountry,
            String locationStreetAddress, String originalPoster, String postingTime, String latitude,
            String longitude) {

        this.locationName = locationName;
        this.locationDescription = locationDescription;
        this.locationCity = locationCity;
        this.locationCountry = locationCountry;
        this.locationStreetAddress = locationStreetAddress;
        this.originalPoster = originalPoster;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        this.originalPostingTime = ZonedDateTime.parse(postingTime, formatter);
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // the needed getters and setter methods
    public String getLocationName() {
        return locationName;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public String getLocationCity() {
        return locationCity;
    }

    public ZonedDateTime getSent() {
        return sent;
    }

    public ZonedDateTime getOriginalPostingTime() {
        return originalPostingTime;
    }

    public long dateAsInt() {
        return originalPostingTime.toInstant().toEpochMilli();

    }

    public void setSent(long epoch) {
        originalPostingTime = ZonedDateTime.ofInstant(Instant.ofEpochMilli(epoch), ZoneOffset.UTC);
    }

    public String getLocationCountry() {
        return locationCountry;
    }

    public String getLocationStreetAddress() {
        return locationStreetAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getOriginalPoster() {
        return originalPoster;
    }

}
