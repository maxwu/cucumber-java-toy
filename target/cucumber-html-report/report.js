$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Sarafi_to_TempConvertor.feature");
formatter.feature({
  "line": 1,
  "name": "Browse the temperature converter page",
  "description": "As a developer,\nIn order to convert temperature\nI want to enter number and check result on google search page \"temperature convertor\".",
  "id": "browse-the-temperature-converter-page",
  "keyword": "Feature"
});
formatter.before({
  "duration": 11379461257,
  "status": "passed"
});
formatter.before({
  "duration": 5010724492,
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
  "duration": 5048496508,
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
  "duration": 1472020088,
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
  "duration": 46331212,
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
  "duration": 452267035,
  "status": "passed"
});
formatter.after({
  "duration": 251143307,
  "status": "passed"
});
formatter.after({
  "duration": 156285,
  "status": "passed"
});
formatter.after({
  "duration": 232152241,
  "status": "passed"
});
formatter.before({
  "duration": 10137020499,
  "status": "passed"
});
formatter.before({
  "duration": 3734327177,
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
  "duration": 198307998,
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
  "duration": 152539334,
  "status": "passed"
});
formatter.match({
  "location": "TemperatureConverterCalStepdef.test_input_data(String,String\u003e)"
});
formatter.result({
  "duration": 1256169,
  "status": "passed"
});
formatter.match({
  "location": "TemperatureConverterCalStepdef.verify_converted_data()"
});
formatter.result({
  "duration": 571529503,
  "status": "passed"
});
formatter.after({
  "duration": 187720514,
  "status": "passed"
});
formatter.after({
  "duration": 26814,
  "status": "passed"
});
formatter.after({
  "duration": 195830084,
  "status": "passed"
});
});