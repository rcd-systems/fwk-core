package systems.rcd.fwk.core.format.xls.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import systems.rcd.fwk.core.format.xls.data.RcdXlsSheet;
import systems.rcd.fwk.core.format.xls.data.RcdXlsWorkbook;

public class RcdXlsWorkbookValidator {

    private final Set<String> expectedSheetNameSet = new HashSet<String>();

    private final Map<String, RcdXlsSheetValidator> sheetValidatorMap = new HashMap<>();

    public RcdXlsWorkbookValidator addExpectedSheetNames(final String... expectedSheetNames) {
        for (final String expectedSheetName : expectedSheetNames) {
            expectedSheetNameSet.add(expectedSheetName);
        }
        return this;
    }

    public RcdXlsWorkbookValidator addSheetValidator(final String sheetName, final RcdXlsSheetValidator sheetValidator) {
        expectedSheetNameSet.add(sheetName);
        sheetValidatorMap.put(sheetName, sheetValidator);
        return this;
    }

    public List<String> validate(final RcdXlsWorkbook workbook) {
        final List<String> errors = new LinkedList<String>();

        for (final String expectedSheetName : expectedSheetNameSet) {
            if (!workbook.containsKey(expectedSheetName)) {
                errors.add("Missing sheet named '" + expectedSheetName + "'");
            }
        }

        for (final Map.Entry<String, RcdXlsSheet> workbookEntry : workbook.entrySet()) {
            final RcdXlsSheetValidator sheetValidator = sheetValidatorMap.get(workbookEntry.getKey());
            if (sheetValidator != null) {
                errors.addAll(sheetValidator.validate(workbookEntry.getKey(), workbookEntry.getValue()));
            }
        }

        return errors;
    }

}
