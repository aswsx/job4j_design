select type.name, count(product) as amount
from type
join product on type.id = product.type_id
group by type.id
order by type.id;