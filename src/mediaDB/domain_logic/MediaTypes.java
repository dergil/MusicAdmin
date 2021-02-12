package mediaDB.domain_logic;

public enum MediaTypes {
    ALL_TYPES("Audio, AudioVideo, InteractiveVideo, LicensedAudio, LicensedAudioVideo, LicensedVideo"),
    AUDIO("Audio"),
    AUDIOVIDEO("AudioVideo"),
    INTERACTIVEVIDEO("InteractiveVideo"),
    LICENSEDAUDIO("LicensedAudio"),
    LICENSEDAUDIOVIDEO("LicensedAudioVideo"),
    LICENSEDVIDEO("LicensedVideo"),
    ;

    private final String text;

    MediaTypes(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
