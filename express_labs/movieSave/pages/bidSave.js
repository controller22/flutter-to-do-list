// require를 사용하여 node-fetch를 불러오기
const express = require('express');
const axios = require('axios');
const {log} = require("debug");
const mysql = require('mysql2/promise');
const app = express();
const PORT = process.env.PORT || 3000;

// import fetch from 'node-fetch'(수정 필요)

const key = '8yOHrEMTLn1y6LxtAIFKWHv3lA9cgXN37fmUyslZ';

const yesterday = moment().subtract(1, 'days').format('DD/MM/YYYY')

const apiUrl = 'https://api.sam.gov/prod/opportunities/v2/search?' +
    'limit=' + limit +
    '&api_key=' + key +
    '&postedFrom=' + yesterday +
    '&postedTo=' + yesterday +
    '&deptname=general&title=korea';
const dbConfig = {
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'g5_write_bid',
};

// MySQL 연결 설정
async function createConnection() {
    try {
        const connection = await mysql.createConnection(dbConfig);
        return connection;
    } catch (error) {
        console.error('Error creating MySQL connection:', error.message);
        throw error;
    }
}


async function axiosDataAndSave() {
    try {
        // Axios를 사용하여 외부 API에 GET 요청 보내기
        const response = await axios.get(apiUrl);

        const bidList = response.opportunitiesData;
        console.log(bidList)
        // MySQL에 데이터 삽입(수정 필요)
        const connection = await createConnection();
        if (bidList===null){
            console.log('Data saved to MySQL');
            connection.end(); // 연결 종료
        }
        for (const data of bidList) {
            // 여기부분을 수정해야할 듯
            const query =
                'INSERT INTO g5_write_bid (movieCd, movieNm, prdtYear, openDt, directors) VALUES (?, ?, ?, ?, ?)';
            const values = [movie.movieCd, movie.movieNm, movie.prdtYear, movie.openDt, directors];
            await connection.execute(query, values);
        }

        console.log('Data saved to MySQL');
        connection.end(); // 연결 종료
    } catch (error) {
        console.error('Error fetching external data:', error.message);
    }
}

// 주기적으로 fetchDataAndSave 함수 실행 ( 24시간 마다 )
const intervalInMilliseconds = 24 * 60 * 60 * 1000; // 5초
setInterval(axiosDataAndSave, intervalInMilliseconds);

// CommonJS 스타일에서는 module.exports를 사용하여 함수를 내보냅니다.
module.exports = axiosDataAndSave;
