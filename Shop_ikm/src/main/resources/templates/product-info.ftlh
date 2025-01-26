<!DOCTYPE html>
<html>
<head>
    <title>Shop</title>
</head>
<body>
<h1>Shop</h1>
<hr>

<h4>Подробная информация о товаре</h4>
<b><p>Название товара: ${product.title}</p><br>
<b><p>Описание товара: ${product.description}</p><br>
<b><p>Цена: ${product.price}</p><br>
<b><p>Город: ${product.city}</p><br>
<b><p>Магазин: ${product.author}</p><br>
<b><p>Категория: ${product.category.name}</p><br>
<b><p>Поставщик: ${product.supplier.name}</p><br>

<hr>

<!-- Кнопка редактирования -->
<a href="/product/edit/${product.id}">
    <button>Редактировать товар</button>
</a>

<!-- Форма для удаления товара -->
<form action="/product/delete/${product.id}" method="post">
    <input type="submit" value="Удалить товар"/>
</form>

</body>
</html>