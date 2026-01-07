// React
/*
	NodeJS SpringBoot
	   |	   |
	 React   React
	------------------- MSA (JWT)
*/
const api = axios.create({
	baseURL:'http://13.125.23.177:8080',
	timeout:50000
})