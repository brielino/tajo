select upper(lower(l_orderkey::text)) as key, count(1) as total from lineitem group by key order by upper(lower(l_orderkey::text)), total;