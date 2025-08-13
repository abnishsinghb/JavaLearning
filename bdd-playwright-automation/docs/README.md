# BDD Playwright Automation Framework - Documentation Index

Welcome to the comprehensive documentation for the BDD Playwright Automation Framework. This index provides quick access to all documentation sections.

## 📚 Documentation Structure

### 🏠 [Main README](../README.md)
The primary documentation file containing:
- Framework overview and architecture
- Technology stack and dependencies
- Project structure and setup instructions
- Quick start guide and basic usage
- Configuration options and best practices

### 📖 Detailed Guides

#### 🚀 [Test Execution Guide](TEST_EXECUTION_GUIDE.md)
Comprehensive guide for running tests in various configurations:
- Test execution strategies (by type, feature, scenario)
- Browser and environment configuration
- Parallel execution setup
- CI/CD integration examples
- Performance monitoring and debugging

#### 🔧 [Framework Development Guide](FRAMEWORK_DEVELOPMENT_GUIDE.md)
Technical guide for extending and maintaining the framework:
- Architecture deep dive
- Adding new components (pages, clients, DTOs)
- Design patterns and best practices
- Code quality guidelines
- Custom reporting and utilities

#### 🌐 [API Testing Guide](API_TESTING_GUIDE.md)
Complete guide for API test automation:
- API testing architecture and patterns
- Creating API clients and data models
- Writing comprehensive API tests
- Authentication and authorization
- Performance testing and validation

#### 🖥️ [Web Testing Guide](WEB_TESTING_GUIDE.md)
Detailed guide for web UI test automation:
- Page Object Model implementation
- Browser configuration and management
- Writing robust web tests
- Handling complex UI interactions
- Responsive and cross-browser testing

## 🎯 Quick Navigation

### For New Users
1. Start with [Main README](../README.md) for setup and overview
2. Follow [Test Execution Guide](TEST_EXECUTION_GUIDE.md) to run your first tests
3. Check [API Testing Guide](API_TESTING_GUIDE.md) or [Web Testing Guide](WEB_TESTING_GUIDE.md) based on your testing needs

### For Developers
1. Review [Framework Development Guide](FRAMEWORK_DEVELOPMENT_GUIDE.md) for architecture
2. Study existing implementations in the codebase
3. Follow code quality guidelines and design patterns
4. Refer to specific testing guides for implementation details

### For Test Automation Engineers
1. Master both [API Testing Guide](API_TESTING_GUIDE.md) and [Web Testing Guide](WEB_TESTING_GUIDE.md)
2. Understand execution strategies in [Test Execution Guide](TEST_EXECUTION_GUIDE.md)
3. Learn framework customization from [Framework Development Guide](FRAMEWORK_DEVELOPMENT_GUIDE.md)

## 📋 Common Use Cases

### Setting Up the Framework
```bash
# Follow these steps from the main README:
1. Install prerequisites (Java 17, Maven, Allure CLI)
2. Clone repository and install dependencies
3. Install Playwright browsers
4. Run sample tests to verify setup
```
👉 **See**: [Main README - Setup Instructions](../README.md#setup-instructions)

### Running Different Types of Tests
```bash
# API tests only
mvn test -Dcucumber.filter.tags="@api"

# Web tests only
mvn test -Dcucumber.filter.tags="@web"

# Smoke tests across both API and Web
mvn test -Dcucumber.filter.tags="@smoke"
```
👉 **See**: [Test Execution Guide - Test Execution Strategies](TEST_EXECUTION_GUIDE.md#test-execution-strategies)

### Generating and Viewing Reports
```bash
# Generate and serve Allure report
allure serve allure-results

# Generate static report
allure generate allure-results --clean -o allure-report
```
👉 **See**: [Main README - Generating Reports](../README.md#generating-reports)

### Adding New Tests

**For API Tests:**
1. Create feature file in `src/test/resources/features/api/`
2. Implement API client in `src/main/java/com/framework/api/client/`
3. Create step definitions in `src/test/java/com/framework/api/stepdefs/`

👉 **See**: [API Testing Guide - Writing API Tests](API_TESTING_GUIDE.md#writing-api-tests)

**For Web Tests:**
1. Create feature file in `src/test/resources/features/web/`
2. Implement page object in `src/main/java/com/framework/web/pages/`
3. Create step definitions in `src/test/java/com/framework/web/stepdefs/`

👉 **See**: [Web Testing Guide - Writing Web Tests](WEB_TESTING_GUIDE.md#writing-web-tests)

### Debugging Issues
1. Enable debug mode with visible browser
2. Add logging and screenshots
3. Use developer tools and console output
4. Check network requests and responses

👉 **See**: 
- [Web Testing Guide - Debugging](WEB_TESTING_GUIDE.md#debugging-and-troubleshooting)
- [API Testing Guide - Debugging](API_TESTING_GUIDE.md#debugging-and-troubleshooting)

## 🔗 External Resources

### Technology Documentation
- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
- [Playwright Java Documentation](https://playwright.dev/java/)
- [REST Assured Documentation](https://rest-assured.io/)
- [Allure Documentation](https://docs.qameta.io/allure/)
- [JUnit 5 Documentation](https://junit.org/junit5/docs/current/user-guide/)

### Best Practices References
- [Page Object Model Pattern](https://martinfowler.com/bliki/PageObject.html)
- [BDD Best Practices](https://cucumber.io/docs/bdd/)
- [API Testing Best Practices](https://assertible.com/blog/7-http-methods-every-api-tester-should-know)

## 📞 Support and Contributions

### Getting Help
1. **Check Documentation**: Search through this documentation first
2. **Review Examples**: Look at existing test implementations in the codebase
3. **Debug Mode**: Enable verbose logging and debug mode for troubleshooting
4. **Community Resources**: Refer to official documentation of underlying tools

### Contributing to Documentation
When adding or updating documentation:
- ✅ Keep examples practical and working
- ✅ Include both positive and negative scenarios
- ✅ Add troubleshooting sections for common issues
- ✅ Update this index when adding new documentation files
- ✅ Follow the established documentation structure and formatting

### Documentation Standards
- Use clear, descriptive headings
- Include code examples with explanations
- Add step-by-step instructions where applicable
- Provide troubleshooting sections
- Keep content up-to-date with framework changes

---

## 📝 Document Change Log

| Version | Date | Changes | Author |
|---------|------|---------|--------|
| 1.0 | 2024-01-01 | Initial documentation creation | Framework Team |

---

**Happy Testing! 🚀**

*This documentation is a living document and should be updated as the framework evolves.*
