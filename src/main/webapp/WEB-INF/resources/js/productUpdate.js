const queryString = window.location.search
const urlParams = new URLSearchParams(queryString)
const targetID = urlParams.get('Product_ID')
const PRODUCT_URL = "http://localhost:8080/iSpanSpring/admin/product/index"
const PRODUCT_TYPE_URL = "http://localhost:8080/iSpanSpring/admin/product/type/index"

let productRawData = []
let productTypeRawData = []
let oldProductName = ""

axios.get(PRODUCT_TYPE_URL)
	.then(response => {
		productTypeRawData = response.data
	})
	.catch(error => { console.log(error) })

axios.get(PRODUCT_URL)
	.then(response => {
		showData(getTargetProduct(response.data))
		addEventListeners(response.data)
	})
	.catch(error => { console.log(error) })

function getTargetProduct(data) {
	return data.find(product => product.product_ID == targetID)
}

function showData(data) {
	let contents = "<tr>"
	contents += "<td id='type' width='155px'>	<input type='text' class='產品種類 typeInput'	name='type'	 value='" + data.product_Type + "'></td>"
	contents += "<td id='id'>					<input type='text' class='產品編號 input'		name='id'	 value='" + data.product_ID + "' disabled ></td>"
	contents += "<td id='name'>					<input type='text' class='產品名稱 input'		name='name'	 value='" + data.product_Name + "'></td>"
	contents += "<td>							<input type='text' class='產品存量 input'		name='stock' value='" + data.product_Stock + "'></td>"
	contents += "<td>							<input type='text' class='產品買價 input'		name='cost'	 value='" + data.product_Cost + "'></td>"
	contents += "<td>							<input type='text' class='產品售價 input'		name='price' value='" + data.product_Price + "'></td>"
	contents += "<td id='imageTD'>				<input type='file' class='產品圖片 input'		name='image'/></td>"
	contents += "<td><button id='button'>Submit</button></td>"
	contents += "<td><button>Cancel</button></td>"
	resultTable.innerHTML = contents
	oldProductName = data.product_Name
}

function addEventListeners(data) {
	const inputs = document.querySelectorAll("table .input")
	const type = document.querySelector("#type")
	const typeInput = document.querySelector(".typeInput")

	type.addEventListener('mouseup', event => {
		if (event.target.value === "-------新增-------") {
			event.target.parentElement.innerHTML = `<label>產品種類</label><input id="type" class="input" type="text" name="type">`
		}
	})

	document.querySelector("#id").addEventListener("click", () => {
		event.preventDefault()
		alert("產品編號不能更改")
	})

	document.querySelector("#name input").addEventListener("change", (event) => {
		for (let i = 0; i < data.length; i++) {
			if (data[i].product_Name.toLowerCase() == event.target.value.trim().toLowerCase()) {
				alert("已有同名稱產品")
				event.target.value = oldProductName
				i = data.length
			}
		}
	})

	typeInput.addEventListener("click", event => {
		let typeContent = "	<select id='type' class='type' name='type'>"
		for (let j = 0; j < productTypeRawData.length; j++) {
			typeContent += `<option value='${productTypeRawData[j].productType_Name}'>${productTypeRawData[j].productType_Name}</option>`
		}
		typeContent += "</select>"
		type.innerHTML = typeContent
	})


	document.querySelector("#editForm").addEventListener("submit", (event) => {
		submitResult.innerHTML = ""
		event.preventDefault()
		let switcher = "on"

		for (let i = 0; i < inputs.length; i++) {
			if (inputs[i].value.trim() == "" && i < 5) {
				switcher = "off"
				submitResult.innerHTML += "請輸入" + inputs[i].classList[0] + "<br>"
			}

			if (i === 2) {
				if (inputs[i].value.match(/\./)) {
					switcher = "off"
					submitResult.innerHTML += inputs[i].classList[0] + "只可輸入整數" + "<br>"
				}
			}

			if (i > 3 && i < 5) {
				if (inputs[i].value.match(/[\`\~\!\@\#\$\%\^\&\*\(\)\_\+\-\=\{\}\[\]\;\:\'\"\<\>\?\,\\]/) ||
					inputs[i].value.match(/[\u4E00-\u9FFF]/) ||
					inputs[i].value.match(/[a-zA-Z]/)) {
					switcher = "off"
					submitResult.innerHTML += inputs[i].classList[0] + "只可輸入數字" + "<br>"
				}
				if (inputs[i].value.match("..")) {
					inputs[i].value = inputs[i].value.replace("..", ".")
				}
			}
		}

		if (switcher === "on") {
			document.querySelector(".產品編號").disabled = false
			event.currentTarget.submit()
		}

	})
}