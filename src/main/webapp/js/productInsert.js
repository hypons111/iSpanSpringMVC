const form = document.querySelector("#insert")
const id = document.querySelector("#id")
const name = document.querySelector("#name")
const supplier = document.querySelector("#supplier")
const type = document.querySelector("#type")
const submit = document.querySelector("#submit")
const inputs = document.querySelectorAll(".input")
const submitResult = document.querySelector("#submitResult")
const PRODUCT_URL = "http://localhost:8080/iSpanSpring/admin/product/index"
const PRODUCT_TYPE_URL = "http://localhost:8080/iSpanSpring/admin/product/type/index"
let productRawData = []
let productTypeRawData = []

axios.get(PRODUCT_TYPE_URL)
	.then(response => {
		productTypeRawData = response.data
		setTypePullDownMenu(productTypeRawData)
	})
	.catch(error => { console.log(error) })


axios
	.get(PRODUCT_URL)
	.then((response) => {
		productRawData = response.data
	})
	.catch((err) => console.log(err));


type.addEventListener('mouseup', event => {
	if(event.target.value === "-------新增-------") {
		event.target.parentElement.innerHTML = `<label>產品種類</label><input id="type" class="input" type="text" name="type">`
	}
})


function setTypePullDownMenu(data) {
	let contents = ""
	data.forEach(type => {
		contents += `<option value='${type.productType_Name}'>${type.productType_Name}</option>`
	})
	type.innerHTML = contents
}

name.addEventListener("change", () => {
	for (let i = 0; i < productRawData.length; i++) {
		if (productRawData[i].P_Name.toLowerCase() === name.value.trim().toLowerCase()) {
			alert("已有同名稱產品")
			name.value = ""
			i = productRawData.length
		}
	}
})

form.addEventListener("submit", (event) => {
	let switcher = "on"
	submitResult.innerText = ""

	inputs.forEach(input => {
		if (input.value.trim() == "") {
			switcher = "off"
			submitResult.innerHTML += "請輸入" + input.previousElementSibling.innerText + "<br>"
			event.preventDefault()
		}
	})

	for (let i = 2; i < inputs.length - 1; i++) {
		if (i === 2) {
			if (inputs[i].value.match(/\./)) {
				switcher = "off"
				event.preventDefault()
				submitResult.innerHTML += inputs[i].previousElementSibling.innerText + "只可輸入整數" + "<br>"
			}
		}
		if (inputs[i].value.match(/[\`\~\!\@\#\$\%\^\&\*\(\)\_\+\-\=\{\}\[\]\;\:\'\"\<\>\?\,\\]/) ||
			inputs[i].value.match(/[\u4E00-\u9FFF]/) ||
			inputs[i].value.match(/[a-zA-Z]/)) {
			switcher = "off"
			event.preventDefault()
			submitResult.innerHTML += inputs[i].previousElementSibling.innerText + "只可輸入數字" + "<br>"
		}
		if (inputs[i].value.match("..")) {
			inputs[i].value = inputs[i].value.replace("..", ".")
		}
	}
	if (switcher === "on") {


		inputs.forEach(input => {
			console.log(input.value)
		})

		event.currentTarget.submit()
	}
})