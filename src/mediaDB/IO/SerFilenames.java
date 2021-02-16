package mediaDB.IO;

public enum  SerFilenames {

        CURRENTSIZE("currentSize.ser"),
        SIZEOBSERVERLIST("sizeObserverList.ser"),
        TAGOCCURENCES("tagOccurences.ser"),
        TAGOBSERVERLIST("tagObserverList.ser"),
        UPLOADABLELIST("uploadableList.ser"),
        PRODUCERSET("producerSet.ser"),
        ADDRESSESSET("addressesSet.ser")
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
