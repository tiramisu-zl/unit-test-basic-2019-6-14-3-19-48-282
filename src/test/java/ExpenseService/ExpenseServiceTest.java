package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Test;

import static ExpenseService.Expense.ExpenseType.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project internalProject = new Project(ProjectType.INTERNAL, "internal");

        // when
        ExpenseType expenseCode = ExpenseService.getExpenseCodeByProjectTypeAndName(internalProject);

        // then
        assertSame(expenseCode, INTERNAL_PROJECT_EXPENSE);
    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project projectA = new Project(ProjectType.EXTERNAL, "Project A");

        // when
        ExpenseType expenseCode = ExpenseService.getExpenseCodeByProjectTypeAndName(projectA);

        // then
        assertSame(expenseCode, EXPENSE_TYPE_A);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project projectB = new Project(ProjectType.EXTERNAL, "Project B");

        // when
        ExpenseType expenseCode = ExpenseService.getExpenseCodeByProjectTypeAndName(projectB);

        // then
        assertSame(expenseCode, EXPENSE_TYPE_B);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project projectC = new Project(ProjectType.EXTERNAL, "Project other");

        // when
        ExpenseType expenseCode = ExpenseService.getExpenseCodeByProjectTypeAndName(projectC);

        // then
        assertSame(expenseCode, OTHER_EXPENSE);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
        Project unexpectedProject = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE, "name");

        // when
        UnexpectedProjectTypeException exception = assertThrows(UnexpectedProjectTypeException.class, () -> {
            ExpenseService.getExpenseCodeByProjectTypeAndName(unexpectedProject);
        });

        // then
        assertSame(exception.getClass(), UnexpectedProjectTypeException.class);
        assertSame(exception.getMessage(), "You enter invalid project type");

    }
}