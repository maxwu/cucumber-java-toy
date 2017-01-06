$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Sarafi_to_TempConvertor.feature");
formatter.feature({
  "line": 1,
  "name": "Browse the temperature converter page",
  "description": "As a developer,\nIn order to convert temperature\nI want to enter number and check result on google search page \"temperature convertor\".",
  "id": "browse-the-temperature-converter-page",
  "keyword": "Feature"
});
formatter.before({
  "duration": 13873723041,
  "status": "passed"
});
formatter.before({
  "duration": 5227731648,
  "status": "passed"
});
formatter.background({
  "line": 6,
  "name": "Web Browser is successfully initialized (Here we launch profiled firefox as an example)",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.scenario({
  "line": 9,
  "name": "Open Temperature Convertor",
  "description": "",
  "id": "browse-the-temperature-converter-page;open-temperature-convertor",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 8,
      "name": "@baseline"
    }
  ]
});
formatter.step({
  "line": 10,
  "name": "Google Entrance Page with:",
  "keyword": "Given ",
  "doc_string": {
    "content_type": "",
    "line": 11,
    "value": "A multiple lines sample parameter.\nThis is the 2nd line.\n-end-line-"
  }
});
formatter.step({
  "line": 16,
  "name": "Search \"temperature converter\"",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "The page title is \"temperature converter\"",
  "keyword": "Then "
});
formatter.step({
  "line": 18,
  "name": "There is a \"Temperature\" option selected",
  "keyword": "And "
});
formatter.match({
  "location": "TemperatureConverterPageStepDef.google_Entrance_page(String)"
});
formatter.result({
  "duration": 5055063496,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "temperature converter",
      "offset": 8
    }
  ],
  "location": "TemperatureConverterPageStepDef.search_Temperature_Converter(String)"
});
formatter.result({
  "duration": 2131739748,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "temperature converter",
      "offset": 19
    }
  ],
  "location": "TemperatureConverterPageStepDef.page_Title_is_Temperature_Converter(String)"
});
formatter.result({
  "duration": 15064764,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Temperature",
      "offset": 12
    }
  ],
  "location": "TemperatureConverterPageStepDef.verify_option_selected(String)"
});
formatter.result({
  "duration": 478913320,
  "status": "passed"
});
formatter.after({
  "duration": 161433,
  "status": "passed"
});
formatter.after({
  "duration": 305189102,
  "status": "passed"
});
formatter.after({
  "duration": 215115460,
  "status": "passed"
});
formatter.before({
  "duration": 10381166417,
  "status": "passed"
});
formatter.before({
  "duration": 6089972290,
  "status": "passed"
});
formatter.background({
  "line": 6,
  "name": "Web Browser is successfully initialized (Here we launch profiled firefox as an example)",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.scenario({
  "comments": [
    {
      "line": 20,
      "value": "# With tags yet, the browser still needs to input key words before executing below feature."
    }
  ],
  "line": 22,
  "name": "Calculate temperature degrees",
  "description": "",
  "id": "browse-the-temperature-converter-page;calculate-temperature-degrees",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 21,
      "name": "@calculation"
    }
  ]
});
formatter.step({
  "line": 23,
  "name": "\"Fahrenheit\" select is present",
  "keyword": "Given "
});
formatter.step({
  "line": 24,
  "name": "\"Celsius\" select is present",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "Input data from the table:",
  "rows": [
    {
      "cells": [
        "100",
        "212"
      ],
      "line": 26
    },
    {
      "cells": [
        "0",
        "32"
      ],
      "line": 27
    },
    {
      "cells": [
        "-100",
        "-148"
      ],
      "line": 28
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 29,
  "name": "Results are correct as on table",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "Fahrenheit",
      "offset": 1
    }
  ],
  "location": "TemperatureConverterCalStepdef.verify_fahrenheit_present(String)"
});
formatter.result({
  "duration": 126543340,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Celsius",
      "offset": 1
    }
  ],
  "location": "TemperatureConverterCalStepdef.verify_celsius_present(String)"
});
formatter.result({
  "duration": 72213754,
  "status": "passed"
});
formatter.match({
  "location": "TemperatureConverterCalStepdef.test_input_data(String,String\u003e)"
});
formatter.result({
  "duration": 3567120,
  "status": "passed"
});
formatter.match({
  "location": "TemperatureConverterCalStepdef.verify_converted_data()"
});
formatter.result({
  "duration": 600007486,
  "status": "passed"
});
formatter.after({
  "duration": 31279,
  "status": "passed"
});
formatter.after({
  "duration": 177183154,
  "status": "passed"
});
formatter.after({
  "duration": 159062686,
  "status": "passed"
});
formatter.scenarioOutline({
  "comments": [
    {
      "line": 31,
      "value": "## Scenario outline sample test"
    }
  ],
  "line": 33,
  "name": "Convert Celsius to Fahrenheit and verify the values",
  "description": "",
  "id": "browse-the-temperature-converter-page;convert-celsius-to-fahrenheit-and-verify-the-values",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 32,
      "name": "@calculation"
    }
  ]
});
formatter.step({
  "line": 34,
  "name": "Google search page with predefined keywords",
  "keyword": "Given "
});
formatter.step({
  "line": 35,
  "name": "Enter Celsius degree as \"\u003ccelsius_degree\u003e\"",
  "keyword": "When "
});
formatter.step({
  "line": 36,
  "name": "Check the value against \"\u003cfahrenheit_degree\u003e\"",
  "keyword": "Then "
});
formatter.examples({
  "line": 37,
  "name": "",
  "description": "",
  "id": "browse-the-temperature-converter-page;convert-celsius-to-fahrenheit-and-verify-the-values;",
  "rows": [
    {
      "cells": [
        "celsius_degree",
        "fahrenheit_degree"
      ],
      "line": 38,
      "id": "browse-the-temperature-converter-page;convert-celsius-to-fahrenheit-and-verify-the-values;;1"
    },
    {
      "cells": [
        "100",
        "212"
      ],
      "line": 39,
      "id": "browse-the-temperature-converter-page;convert-celsius-to-fahrenheit-and-verify-the-values;;2"
    },
    {
      "cells": [
        "0",
        "32"
      ],
      "line": 40,
      "id": "browse-the-temperature-converter-page;convert-celsius-to-fahrenheit-and-verify-the-values;;3"
    },
    {
      "cells": [
        "-100",
        "-148"
      ],
      "line": 41,
      "id": "browse-the-temperature-converter-page;convert-celsius-to-fahrenheit-and-verify-the-values;;4"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 10223911232,
  "status": "passed"
});
formatter.before({
  "duration": 5044683228,
  "status": "passed"
});
formatter.background({
  "line": 6,
  "name": "Web Browser is successfully initialized (Here we launch profiled firefox as an example)",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.scenario({
  "line": 39,
  "name": "Convert Celsius to Fahrenheit and verify the values",
  "description": "",
  "id": "browse-the-temperature-converter-page;convert-celsius-to-fahrenheit-and-verify-the-values;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 32,
      "name": "@calculation"
    }
  ]
});
formatter.step({
  "line": 34,
  "name": "Google search page with predefined keywords",
  "keyword": "Given "
});
formatter.step({
  "line": 35,
  "name": "Enter Celsius degree as \"100\"",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 36,
  "name": "Check the value against \"212\"",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "TemperatureConverterCalStepdef.verify_page_title()"
});
formatter.result({
  "duration": 9443869,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "100",
      "offset": 25
    }
  ],
  "location": "TemperatureConverterCalStepdef.enter_celsius_degree(String)"
});
formatter.result({
  "duration": 139716761,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "212",
      "offset": 25
    }
  ],
  "location": "TemperatureConverterCalStepdef.check_fahrenheit_degree(String)"
});
formatter.result({
  "duration": 49995205,
  "status": "passed"
});
formatter.after({
  "duration": 32944,
  "status": "passed"
});
formatter.after({
  "duration": 183000827,
  "status": "passed"
});
formatter.after({
  "duration": 152860810,
  "status": "passed"
});
formatter.before({
  "duration": 11518451559,
  "status": "passed"
});
formatter.before({
  "duration": 6381917483,
  "status": "passed"
});
formatter.background({
  "line": 6,
  "name": "Web Browser is successfully initialized (Here we launch profiled firefox as an example)",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.scenario({
  "line": 40,
  "name": "Convert Celsius to Fahrenheit and verify the values",
  "description": "",
  "id": "browse-the-temperature-converter-page;convert-celsius-to-fahrenheit-and-verify-the-values;;3",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 32,
      "name": "@calculation"
    }
  ]
});
formatter.step({
  "line": 34,
  "name": "Google search page with predefined keywords",
  "keyword": "Given "
});
formatter.step({
  "line": 35,
  "name": "Enter Celsius degree as \"0\"",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 36,
  "name": "Check the value against \"32\"",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "TemperatureConverterCalStepdef.verify_page_title()"
});
formatter.result({
  "duration": 22026301,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "0",
      "offset": 25
    }
  ],
  "location": "TemperatureConverterCalStepdef.enter_celsius_degree(String)"
});
formatter.result({
  "duration": 143228730,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "32",
      "offset": 25
    }
  ],
  "location": "TemperatureConverterCalStepdef.check_fahrenheit_degree(String)"
});
formatter.result({
  "duration": 65618466,
  "status": "passed"
});
formatter.after({
  "duration": 63691,
  "status": "passed"
});
formatter.after({
  "duration": 229111090,
  "status": "passed"
});
formatter.after({
  "duration": 154581913,
  "status": "passed"
});
formatter.before({
  "duration": 9870009033,
  "status": "passed"
});
formatter.before({
  "duration": 7911821766,
  "status": "passed"
});
formatter.background({
  "line": 6,
  "name": "Web Browser is successfully initialized (Here we launch profiled firefox as an example)",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.scenario({
  "line": 41,
  "name": "Convert Celsius to Fahrenheit and verify the values",
  "description": "",
  "id": "browse-the-temperature-converter-page;convert-celsius-to-fahrenheit-and-verify-the-values;;4",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 32,
      "name": "@calculation"
    }
  ]
});
formatter.step({
  "line": 34,
  "name": "Google search page with predefined keywords",
  "keyword": "Given "
});
formatter.step({
  "line": 35,
  "name": "Enter Celsius degree as \"-100\"",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 36,
  "name": "Check the value against \"-148\"",
  "matchedColumns": [
    1
  ],
  "keyword": "Then "
});
formatter.match({
  "location": "TemperatureConverterCalStepdef.verify_page_title()"
});
formatter.result({
  "duration": 18602388,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "-100",
      "offset": 25
    }
  ],
  "location": "TemperatureConverterCalStepdef.enter_celsius_degree(String)"
});
formatter.result({
  "duration": 230184578,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "-148",
      "offset": 25
    }
  ],
  "location": "TemperatureConverterCalStepdef.check_fahrenheit_degree(String)"
});
formatter.result({
  "duration": 113049079,
  "status": "passed"
});
formatter.after({
  "duration": 24750,
  "status": "passed"
});
formatter.after({
  "duration": 338253310,
  "status": "passed"
});
formatter.after({
  "duration": 345509103,
  "status": "passed"
});
});