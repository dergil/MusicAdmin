package mediaDB;

//Source: https://stackoverflow.com/questions/3978654/best-way-to-create-enum-of-strings
public enum ExampleInput {
    INTERACTIVEVIDEO_0("InteractiveVideo Produzent1 Lifestyle,News 5000 3600 DWT 640 480 Abstimmung"),
    LICENSEDAUDIOVIDEO_0("LicensedAudioVideo Produzent1 , 8000 600 DCT 1400 900 44100 EdBangerRecords"),
    PRODUCER_0("Produzent1")
    ;

    private final String text;

    ExampleInput(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
