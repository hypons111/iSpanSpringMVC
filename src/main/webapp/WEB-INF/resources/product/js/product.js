const BASE_URL = "http://localhost:8080/iSpanSpringMVC/admin/product/productjson";
const resultTable = document.querySelector("#resultTable")
const id = document.querySelector('#id')
const columnSearchs = document.querySelectorAll('.columnSearch')
const columnSearchInputs = document.querySelectorAll('.columnSearchInput')
const sorts = document.querySelectorAll('#sort')
let currentData = []
let rawData = ""
let sortStates = "ASC"

// 取得 json
axios
	.get(BASE_URL)
	.then(response => {
		return new Promise(resolve => {
			resolve(response.data)
			rawData = response.data
		})
	})
	.then(rawData => {
		showData(rawData)
		addSortEventListeners()
		addSearchEventListeners()
	})
	.catch(error => console.log(error));


function addSortEventListeners() {
	// 安裝排序 listener
	sorts.forEach(sort => {
		sort.addEventListener('click', (event) => {
			event.preventDefault()
			let attribute = event.target.classList[0]
			if (sortStates === "ASC") {
				currentData.sort((a, b) => {
					if (a[attribute] < b[attribute]) { return -1 }
					if (a[attribute] > b[attribute]) { return 1 }
					return 0
				})
				sortStates = "DESC"
			} else {
				currentData.sort((a, b) => {
					if (a[attribute] < b[attribute]) { return 1 }
					if (a[attribute] > b[attribute]) { return -1 }
					return 0
				})
				sortStates = "ASC"
			}
			showData(currentData)
		})
	})
}

function addSearchEventListeners() {
	// 安裝搜尋 listener
	columnSearchInputs.forEach(columnSearchInput => {
		columnSearchInput.addEventListener("keyup", (event) => {
			showData(ultraFuckingSearch())
		})
	})
}

//show all data button listener
document.querySelector('#showAll').addEventListener('click', () => {
	showData(rawData)
	columnSearchInputs.forEach(columnSearchInput => {
		columnSearchInput.value = ""
	})
})

// show data
function showData(data) {
	currentData = []
	currentData.push(...data)
	document.querySelector("#totalNum").innerText = data.length
	contents = ""
	for (let i = 0; i < data.length; i++) {
		contents += "<tr><td>" + (i + 1) + "</td>"
		contents += "<td>" + data[i].product_Type + "</td>"
		contents += "<td>" + data[i].product_ID + "</td>"
		contents += "<td>" + data[i].product_Name + "</td>"
		contents += "<td>" + data[i].product_Stock + "</td>"
		contents += "<td>" + data[i].product_Cost + "</td>"
		contents += "<td>" + data[i].product_Price + "</td>"
		contents += "<td><img src='image/" + data[i].product_Image + "?" + Math.random() + "' width='50px'></td>"
	         //	         <img src="images/member/${member.imagefilename}" style="width:100px;">
		contents += "<td><a href=update?Product_ID=" + data[i].product_ID + "><button>Edit</button></a></td>"
		contents += "<td><a href=delete?Product_ID=" + data[i].product_ID + "><button>Delete</button></a></td></tr>"
	}
	resultTable.innerHTML = contents
}

function ultraFuckingSearch() {
	let tempData = rawData
	const KEY = ["", "product_ID", "", "product_Stock", "product_Cost", "product_Price"]
	for (let k = 0; k < columnSearchInputs.length; k++) {

		if (columnSearchInputs[k].value !== "") {
			if (k === 0) {
				tempData = tempData.filter(product => product.product_Type.toLowerCase().includes(columnSearchInputs[0].value))
			} else if (k === 2) {
				tempData = tempData.filter(product => product.product_Name.toLowerCase().includes(columnSearchInputs[2].value.toLowerCase()))
			} else {
				if (columnSearchInputs[k].value.includes("<")) {
					tempData = tempData.filter(product => product[KEY[k]] < Number(columnSearchInputs[k].value.slice(1)))
				} else if (columnSearchInputs[k].value.includes(">")) {
					tempData = tempData.filter(product => product[KEY[k]] > Number(columnSearchInputs[k].value.slice(1)))
				} else {
					tempData = tempData.filter(product => product[KEY[k]] == Number(columnSearchInputs[k].value))
				}
			}
		}
	}
	return tempData
}
