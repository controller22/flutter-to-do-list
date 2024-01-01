//import liraries
import React, { useContext, useEffect, useState } from 'react';
import { StyleSheet } from 'react-native';
import ExpensesOutput from '../components/ExpensesOutput/ExpensesOutput';
import { getDateMinusDays } from '../util/date';
import { ExpensesContext } from '../store/expeneses-context';
import { fetchExpenses } from '../util/http';
import LoadingOverlay from '../components/UI/LoadingOverlay';
import ErrorOverlay from '../components/UI/ErrorOverlay';

// create a component
function RecentExpenses() {
    const [error,setError]=useState();
    const [isFetching, setIsfetching] = useState(true)
    const expensesCtx = useContext(ExpensesContext);

    useEffect(() => {
        async function getExpenses() {
            setIsfetching(true);
            try {
                const expenses = await fetchExpenses();
                expensesCtx.setExpenses(expenses);
            } catch (error) {
                setError("가계부의 데이터를 불러오지 못했습니다.");
            }
            setIsfetching(false);
        }
        getExpenses();
    }, []);


    const recentExpenses = expensesCtx.expenses.filter((expense) => {
        const today = new Date();
        const date7DaysAgo = getDateMinusDays(today, 7);

        return expense.date > date7DaysAgo && expense.date < today;
    })

    if (isFetching) {
        return <LoadingOverlay /> 
    }
    if (error&&!isFetching) {
        return <ErrorOverlay message={error} /> 
    }

    return (
        <ExpensesOutput
            expenses={recentExpenses}
            expensesPeriod="Recent 7days"
            fallbackText="No expenses registered for the last 7 days"
        />
    );
};

// define your styles
const styles = StyleSheet.create({

});

//make this component available to the app
export default RecentExpenses;
