$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("Sarafi_to_TempConvertor.feature");
formatter.feature({
  "line": 1,
  "name": "Browse the temperature converter page",
  "description": "As a developer,\nIn order to convert temperature\nI want to enter number and check result on google search page \"temperature convertor\".",
  "id": "browse-the-temperature-converter-page",
  "keyword": "Feature"
});
formatter.before({
  "duration": 24895033679,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Open Temperature Convertor",
  "description": "",
  "id": "browse-the-temperature-converter-page;open-temperature-convertor",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "Google Entrance Page",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "Search \"temperature converter\"",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "The page title is \"temperature converter\"",
  "keyword": "Then "
});
formatter.step({
  "line": 10,
  "name": "There is a \"Temperature\" option selected",
  "keyword": "And "
});
formatter.match({
  "location": "TemperatureConverterTestStepDef.google_Entrance_page()"
});
formatter.result({
  "duration": 4102012008,
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
  "duration": 2200362084,
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
  "duration": 16059785,
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
  "duration": 27128918,
  "status": "passed"
});
formatter.after({
  "duration": 329739784,
  "status": "passed"
});
});