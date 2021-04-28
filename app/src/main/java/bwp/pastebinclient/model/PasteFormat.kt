package bwp.pastebinclient.model

enum class PasteFormat(val short: String, val full: String) {
    _4cs("4cs", "4CS"),
    _6502acme("6502acme", "6502 ACME Cross Assembler"),
    _6502kickass("6502kickass", "6502 Kick Assembler"),
    _6502tasm("6502tasm", "6502 TASM/64TASS"),
    _abap("abap", "ABAP"),
    _actionscript("actionscript", "ActionScript"),
    _actionscript3("actionscript3", "ActionScript 3")
    // TODO fill enum with possible languages
}