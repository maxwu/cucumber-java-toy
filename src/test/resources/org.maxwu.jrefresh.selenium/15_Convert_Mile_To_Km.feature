Feature: Convert value in mile to km
  Scenario Outline: Convert number of mile to km
    Given Select "Length" dimension
      And Select "Mile" unit in left
      And Select "Kilometre" unit in right
    When  Enter "<mile>" in left input
    Then  Get "<kilometer>" on right input
    Examples:
      | mile | kilometer |
      | 1    | 1.60934   |
      | 0    | 0         |
      | 10   | 16.0934   |