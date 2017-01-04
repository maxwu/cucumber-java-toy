$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Sarafi_to_TempConvertor.feature");
formatter.feature({
  "line": 1,
  "name": "Browse the temperature converter page",
  "description": "As a developer,\nIn order to convert temperature\nI want to enter number and check result on google search page \"temperature convertor\".",
  "id": "browse-the-temperature-converter-page",
  "keyword": "Feature"
});
formatter.before({
  "duration": 20933743823,
  "status": "passed"
});
formatter.scenario({
  "line": 7,
  "name": "Open Temperature Convertor",
  "description": "",
  "id": "browse-the-temperature-converter-page;open-temperature-convertor",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 6,
      "name": "@baseline"
    }
  ]
});
formatter.step({
  "line": 8,
  "name": "Google Entrance Page with:",
  "keyword": "Given ",
  "doc_string": {
    "content_type": "",
    "line": 9,
    "value": "A multiple lines sample parameter.\nThis is the 2nd line.\n-end-line-"
  }
});
formatter.step({
  "line": 14,
  "name": "Search \"temperature converter\"",
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "The page title is \"temperature converter\"",
  "keyword": "Then "
});
formatter.step({
  "line": 16,
  "name": "There is a \"Temperature\" option selected",
  "keyword": "And "
});
formatter.match({
  "location": "TemperatureConverterTestStepDef.google_Entrance_page(String)"
});
formatter.result({
  "duration": 5607740274,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "temperature converter",
      "offset": 8
    }
  ],
  "location": "TemperatureConverterTestStepDef.search_Temperature_Converter(String)"
});
formatter.result({
  "duration": 1691294258,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "temperature converter",
      "offset": 19
    }
  ],
  "location": "TemperatureConverterTestStepDef.page_Title_is_Temperature_Converter(String)"
});
formatter.result({
  "duration": 14487478,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Temperature",
      "offset": 12
    }
  ],
  "location": "TemperatureConverterTestStepDef.verify_option_selected(String)"
});
formatter.result({
  "duration": 703282834,
  "status": "passed"
});
formatter.after({
  "duration": 341604196,
  "status": "passed"
});
});