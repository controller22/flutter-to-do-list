//import liraries
import React, { useContext, useLayoutEffect, useState } from 'react';
import { StyleSheet, View } from 'react-native';
import IconButton from '../components/UI/IconButton';
import ExpenseForm from '../components/manageExpense/ExpenseForm';
import { GlobalStyles } from '../constants/styles';
import { ExpensesContext } from '../store/expeneses-context';
import { deleteExpense, storeExpense, updateExpense } from '../util/http';
import LoadingOverlay from '../components/UI/LoadingOverlay';
import ErrorOverlay from '../components/UI/ErrorOverlay';

// create a component
function ManageExpense({ route, navigation }) {
    const [error, setError] = useState();
    const [loading, setLoading] = useState(false);
    const expensesCtx = useContext(ExpensesContext);

    const editedexpenseId = route.params?.expenseId;
    const isEditing = !!editedexpenseId;

    const seletedExpense = expensesCtx.expenses.find(
        (expense) => expense.id === editedexpenseId
    );

    useLayoutEffect(() => {
        navigation.setOptions({
            title: isEditing ? 'Edit Expense' : 'Add Expense'
        });
    }, [navigation, isEditing])

    async function deleteExpenseHandler() {
        setLoading(true);
        try {
            await deleteExpense(editedexpenseId);
            expensesCtx.deleteExpense(editedexpenseId);
            navigation.goBack();
        } catch (error) {
            setError('제거할 수 없습니다. - 나중에 다시 시도 해주세요');
            setLoading(false);
        }

    }

    if (error && !loading) {
        return <ErrorOverlay message={error} />
    }

    function cancelHandler() {
        navigation.goBack();
    }

    async function confirmHandler(expenseData) {
        setLoading(true);
        try {
            if (isEditing) {
                expensesCtx.updateExpense(editedexpenseId, expenseData);
                await updateExpense(editedexpenseId, expenseData);
            } else {
                const id = await storeExpense(expenseData);
                expensesCtx.addExpense({ ...expenseData, id: id });
            }
            navigation.goBack();
        } catch (error) {
            setError('데이터를 저장할 수 없습니다. - 잠시 후 다시 시도 해 주세요');
            setLoading(false)
        }
    }

    if (loading) {
        return <LoadingOverlay />
    }

    return (
        <View style={styles.container}>
            <ExpenseForm
                submitButtonLabel={isEditing ? '수정하기' : '추가하기'}
                onSubmit={confirmHandler}
                onCancel={cancelHandler}
                defaultValues={seletedExpense}
            />

            {isEditing && (
                <View style={styles.deleteContainer}>
                    <IconButton
                        icon="trash"
                        color={GlobalStyles.colors.error500}
                        size={36}
                        onPress={deleteExpenseHandler}
                    />
                </View>
            )}
        </View>
    );
};

// define your styles
const styles = StyleSheet.create({
    container: {
        flex: 1,
        padding: 24,
        backgroundColor: GlobalStyles.colors.primary800,
    },

    deleteContainer: {
        marginTop: 16,
        paddingTop: 8,
        borderTopWidth: 2,
        borderTopColor: GlobalStyles.colors.primary200,
        alignItems: 'center',
    }
});

//make this component available to the app
export default ManageExpense;
