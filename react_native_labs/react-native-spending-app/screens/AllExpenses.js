//import liraries
import React, { useContext } from 'react';
import { StyleSheet } from 'react-native';
import ExpensesOutput from '../components/ExpensesOutput/ExpensesOutput';
import { ExpensesContext } from '../store/expeneses-context';

// create a component
function AllExpenses() {
    const expensesCtx = useContext(ExpensesContext);

    return (
        <ExpensesOutput 
        expenses={expensesCtx.expenses} 
        expensesPeriod="Total" 
        fallbackText="No registered expenses found!"/>
    );
};

// define your styles
const styles = StyleSheet.create({

});

//make this component available to the app
export default AllExpenses;
