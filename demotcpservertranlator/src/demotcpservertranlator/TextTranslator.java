package demotcpservertranlator;

import java.util.HashMap;

import java.util.Map;

/**
 * This class, TextTranslator, represents a text translator with support for multiple languages. 
 * It contains a
 * HashMap that stores translations for various sentences in different languages.
 * 
 * 
 * @author isratjahanbhuiyan
 *
 */
public class TextTranslator {
    private Map<String, Map<String, String>> translations;

    public TextTranslator() {
        translations = new HashMap<>();

        // English translations
        Map<String, String> enTranslations = new HashMap<>();
        enTranslations.put("Good morning", "Good morning");
        enTranslations.put("Good afternoon", "Good afternoon");
        enTranslations.put("Good evening", "Good evening");
        enTranslations.put("How are you?", "How are you?");
        enTranslations.put("Thank you", "Thank you");
        enTranslations.put("Goodbye", "Goodbye");
        translations.put("en", enTranslations);

        // Bahasa Malaysia translations
        Map<String, String> msTranslations = new HashMap<>();
        msTranslations.put("Good morning", "Selamat pagi");
        msTranslations.put("Good afternoon", "Selamat petang");
        msTranslations.put("Good evening", "Selamat malam");
        msTranslations.put("How are you?", "Apa khabar?");
        msTranslations.put("Thank you", "Terima kasih");
        msTranslations.put("Goodbye", "Selamat tinggal");
        translations.put("ms", msTranslations);

        // Arabic translations
        Map<String, String> arTranslations = new HashMap<>();
        arTranslations.put("Good morning", "صباح الخير");
        arTranslations.put("Good afternoon", "مساء الخير");
        arTranslations.put("Good evening", "مساء الخير");
        arTranslations.put("How are you?", "كيف حالك؟");
        arTranslations.put("Thank you", "شكراً لك");
        arTranslations.put("Goodbye", "وداعاً");
        translations.put("ar", arTranslations);

        // Korean translations
        Map<String, String> koTranslations = new HashMap<>();
        koTranslations.put("Good morning", "좋은 아침");
        koTranslations.put("Good afternoon", "좋은 오후");
        koTranslations.put("Good evening", "좋은 저녁");
        koTranslations.put("How are you?", "어떻게 지내세요?");
        koTranslations.put("Thank you", "감사합니다");
        koTranslations.put("Goodbye", "안녕히 가세요");
        translations.put("ko", koTranslations);
    }

    public String translateText(String text, String targetLanguageCode) {
        Map<String, String> languageTranslations = translations.get(targetLanguageCode);
        if (languageTranslations != null) {
            String translation = languageTranslations.get(text);
            if (translation != null) {
                return translation;
            } else {
                return "Translation not available for the selected sentence in the target language.";
            }
        } else {
            return "Translation not available for the target language code.";
        }
    }
}
