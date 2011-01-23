package org.lumine.util;

/**
 * Provides utility functions for dealing with strings.
 */
public final class Strings {
    /**
     * Calculates the edit distance between two strings using the Levenshtein
     * distance algorithm.
     * 
     * @param sourceString
     *            The first, "source" string.
     * @param targetString
     *            The second, "target" string.
     * @return The edit distance between the two strings. The higher this value
     *         is, the more different the strings.
     */
    public static int editDistance(final String sourceString,
            final String targetString) {
        final int sourceLength = sourceString.length();
        final int targetLength = targetString.length();
        if (sourceLength == 0)
            return targetLength;
        if (targetLength == 0)
            return sourceLength;
        int prevCostArray[] = new int[sourceLength + 1];
        int costArray[] = new int[sourceLength + 1];

        int sourcePos;
        int targetPos;

        for (sourcePos = 0; sourcePos <= sourceLength; sourcePos++) {
            prevCostArray[sourcePos] = sourcePos;
        }

        for (targetPos = 1; targetPos <= targetLength; targetPos++) {
            final char currentChar = targetString.charAt(targetPos - 1);
            costArray[0] = targetPos;

            for (sourcePos = 1; sourcePos <= sourceLength; sourcePos++) {
                final int cost = sourceString.charAt(sourcePos - 1) == currentChar ? 0
                        : 1;
                costArray[sourcePos] = Math.min(Math.min(
                        costArray[sourcePos - 1] + 1,
                        prevCostArray[sourcePos] + 1),
                        prevCostArray[sourcePos - 1] + cost);
            }

            final int tmpArray[] = prevCostArray;
            prevCostArray = costArray;
            costArray = tmpArray;
        }

        return prevCostArray[sourceLength];
    }

    public static String nth(final int n) {
        final int mod = n % 10;
        switch (mod) {
        case 1:
            return n + "st";
        case 2:
            return n + "nd";
        case 3:
            return n + "rd";
        default:
            return n + "th";
        }
    }

    private Strings() {
    }
}
