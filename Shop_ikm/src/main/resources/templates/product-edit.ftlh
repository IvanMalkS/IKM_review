<!DOCTYPE html>
<html>
<head>
    <title>Редактировать товар</title>
</head>
<body>
<h1>Редактирование товара</h1>
<form action="/product/edit/${product.id}" method="post">
    <p for="title">Название товара:</p>
    <input type="text" name="title" value="${product.title}"/><br><br>

    <p for="description">Описание:</p>
    <input type="text" name="description" value="${product.description}"/><br><br>

    <p for="price">Цена:</p>
    <input type="number" name="price" value="${product.price}"/><br><br>

    <p for="city">Город:</p>
    <input type="text" name="city" value="${product.city}"/><br><br>

    <p for="author">Магазин:</p>
    <input type="text" name="author" value="${product.author}"/><br><br>

    <p for="category">Категория:</p>
    <select name="categoryId">
        <#list categories as category>
            <option value="${category.id}" <#if category.id == product.category.id>selected</#if>>${category.name}</option>
        </#list>
    </select><br><br>

    <p for="supplier">Поставщик:</p>
    <select name="supplierId">
        <#list suppliers as supplier>
            <option value="${supplier.id}" <#if supplier.id == product.supplier.id>selected</#if>>${supplier.name}</option>
        </#list>
    </select><br><br>

    <input type="submit" value="Сохранить изменения"/>
</form>
</body>
</html>