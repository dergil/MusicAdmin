package mediaDB.IO;

public enum  SerFilenames {

        CURRENTSIZE("currentSize.ser"),
        TAGOCCURENCES("tagOccurences.ser"),
        UPLOADABLELIST("uploadableList.ser"),
        PRODUCERSET("producerSet.ser"),
        ADDRESSESSET("addressesSet.ser"),
        RANDOMACCESSSAVEDMEDIAFILES("randomAccessSavedMediaFiles.ser"),
        RANDOMACCESSCURRENTOFFSET("randomAccessCurrentOffset")
        ;

        private final String text;

        SerFilenames(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
}
