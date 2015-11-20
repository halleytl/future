select
    count(gi) as pv,
    count(distinct gi) as uv
from
    helix_mxyc*
group by business_id
