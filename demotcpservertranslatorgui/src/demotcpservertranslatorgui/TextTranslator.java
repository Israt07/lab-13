package demotcpservertranslatorgui;

import java.util.HashMap;

import java.util.Map;

/**
 * The TextTranslator class is responsible for handling text translation logic based on language codes.
 * It stores translations for different languages in a map and provides a method to translate text.
 * 
 * 
 * @author isratjahanbhuiyan
 *
 */
public class TextTranslator {
    private Map<Integer, Map<String, String>> translations;

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
        translations.put(1, enTranslations);

        // Bahasa Malaysia translations
        Map<String, String> msTranslations = new HashMap<>();
        msTranslations.put("Good morning", "Selamat pagi");
        msTranslations.put("Good afternoon", "Selamat petang");
        msTranslations.put("Good evening", "Selamat malam");
        msTranslations.put("How are you?", "Apa khabar?");
        msTranslations.put("Thank you", "Terima kasih");
        msTranslations.put("Goodbye", "Selamat tinggal");
        translations.put(2, msTranslations);

        // Arabic translations
        Map<String, String> arTranslations = new HashMap<>();
        arTranslations.put("Good morning", "صباح الخير");
        arTranslations.put("Good afternoon", "مساء الخير");
        arTranslations.put("Good evening", "مساء الخير");
        arTranslations.put("How are you?", "كيف حالك؟");
        arTranslations.put("Thank you", "شكراً لك");
        arTranslations.put("Goodbye", "وداعاً");
        translations.put(3, arTranslations);

        // Korean translations
        Map<String, String> koTranslations = new HashMap<>();
        koTranslations.put("Good morning", "좋은 아침");
        koTranslations.put("Good afternoon", "좋은 오후");
        koTranslations.put("Good evening", "좋은 저녁");
        koTranslations.put("How are you?", "어떻게 지내세요?");
        koTranslations.put("Thank you", "감사합니다");
        koTranslations.put("Goodbye", "안녕히 가세요");
        translations.put(4, koTranslations);
    }

    public String translateText(String text, int languageCode) {
        Map<String, String> languageTranslations = translations.get(languageCode);
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
