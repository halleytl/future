-- shop.sh

select
    business_id,
    count(distinct oinfo.order_id) as info_count,
    sum(ogoods.goods_number) as count,
    sum(ogoods.goods_paid_price) as amount
from
    ecs_order_payment as opay
    inner join ecs_order_info as oinfo using (pay_order_id)
    inner join ecs_order_goods as ogoods using (order_id)
    inner join ecs_business_info as binfo using (business_id)
where
    opay.pay_order_total > 1
    and opay.pay_order_status = {pay_order_status}
    and opay.payedtime >= curdate()
group by business_id
