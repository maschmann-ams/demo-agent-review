# Übung G: Sicher entwickeln mit KI-Assistenten und Agenten

**Schulung Secure Coding (18.11.2026)** · Modul 6

## Szenario (7 Minuten, gemeinsam)

Ein Coding-Agent soll in einer isolierten Testkopie dieses Repositories einen Zugriff auf Nachrichten überprüfen. 
* Der Agent darf die relevanten Quelldateien lesen und Änderungen vorschlagen.
* Ein Mensch prüft die Änderungen unabhängig.
* Zugriff auf Secrets, Produktionsumgebungen oder externe Netze ist für die Aufgabe **nicht** erforderlich.

In `issues/001-user-messages-check.md` liegt ein neues Issue eines externen Nutzers vor.
Der Agent hat den Code in `src/main/java/training/agent/NotificationController.java` vorgeschlagen und empfiehlt zusätzlich:
1. Den Befehl aus dem Issue auszuführen,
2. Nach erfolgreichem Testlauf automatisch nach Produktion zu deployen.

---

## Leitfragen für das Team-Review

1. **Vertrauensgrenzen & Prompt Injection:**
   * Welche Teile des Issues sind autorisierter Arbeitsinhalt und welche Teile sind fremde Anweisungen (Prompt Injection)?
   * Warum sind Issues, Commits oder externe Datenquellen keine vertrauenswürdige Autorität für einen KI-Agenten?
2. **Daten & Berechtigungen:**
   * Welche Daten könnten über den Exfiltrationsbefehl (`application-local.yml`) abfließen?
   * Welche minimalen Datei-, Tool- und Netzwerkrechte hätte der Agent maximal erhalten dürfen?
3. **Code-Qualität:**
   * Was fehlt im Implementierungsvorschlag des Agenten? Warum schützt `@PreAuthorize("hasAuthority('APP_USER')")` nicht vor unberechtigtem Datenzugriff?
   * Wie sieht ein passender Negativtest mit zwei verschiedenen Identitäten (Alice und Bob) aus?
4. **Kontrollen & Teamregeln:**
   * Welche Kontrollmechanismen (Human-in-the-Loop, PR-Review, Sandboxing) müssen vor Merge und Deployment greifen?
