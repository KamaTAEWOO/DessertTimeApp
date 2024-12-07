---
name: Bug report
about: Create a report to help us improve
title: ''
labels: Bug
assignees: KamaTAEWOO

---

name: "Bug Report"
description: "Report a bug to help us improve."
labels: ["bug"]
body:
  - type: markdown
    text: "Thank you for taking the time to report a bug. Please fill out the details below."
  - type: input
    id: title
    attributes:
      label: "Bug Title"
      description: "A short, clear title describing the bug."
      placeholder: "Bug title..."
      required: true
  - type: textarea
    id: description
    attributes:
      label: "Bug Description"
      description: "Provide a detailed description of the bug, including steps to reproduce and any relevant context."
      placeholder: "Describe the bug in detail..."
      required: true
  - type: input
    id: steps-to-reproduce
    attributes:
      label: "Steps to Reproduce"
      description: "List the steps to reproduce the bug."
      placeholder: "1. Step one\n2. Step two\n..."
      required: true
  - type: input
    id: expected-behavior
    attributes:
      label: "Expected Behavior"
      description: "Describe what you expected to happen."
      placeholder: "Expected behavior..."
      required: true
  - type: input
    id: actual-behavior
    attributes:
      label: "Actual Behavior"
      description: "Describe what actually happened."
      placeholder: "Actual behavior..."
      required: true
  - type: dropdown
    id: severity
    attributes:
      label: "Severity"
      description: "How severe is this bug?"
      options:
        - "Critical"
        - "Major"
        - "Minor"
        - "Trivial"
      required: true
  - type: input
    id: environment
    attributes:
      label: "Environment"
      description: "Provide details about your environment (e.g., OS, browser, app version)."
      placeholder: "e.g., Windows 10, Chrome 114, v1.2.3"
      required: true
  - type: textarea
    id: additional-context
    attributes:
      label: "Additional Context"
      description: "Add any other context about the problem here."
      placeholder: "Additional context..."
      required: false
