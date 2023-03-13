package com.fengshuoliu;

/**
 * 思考
 * ◼如果要经常判断 1 个元素是否存在，你会怎么做?
 * 很容易想到使用哈希表(HashSet、HashMap)，将元素作为 key 去查找
 * ✓ 时间复杂度:O(1)，但是空间利用率不高，需要占用比较多的内存资源
 * <p>
 * ◼ 如果需要编写一个网络爬虫去爬10亿个网站数据，为了避免爬到重复的网站，如何判断某个网站是否爬过?
 * 很显然，HashSet、HashMap 并不是非常好的选择
 * <p>
 * ◼ 是否存在时间复杂度低、占用内存较少的方案?
 * 布隆过滤器(Bloom Filter)
 * ◼ 优缺点
 * 优点:空间效率和查询时间都远远超过一般的算法
 * 缺点:有一定的误判率、删除困难
 * ◼ 它实质上是一个很长的二进制向量和一系列随机映射函数(Hash函数)
 * ◼ 常见应用
 * 网页黑名单系统、垃圾邮件过滤系统、爬虫的网址判重系统、解决缓存穿透问题
 */
public class Main {

    static void test1() {
        BloomFilter<Integer> bf = new BloomFilter<>(1_00_0000, 0.01);
        for (int i = 1; i <= 1_00_0000; i++) {
            bf.put(i);
        }

        int count = 0; // 误判个数
        for (int i = 1_00_0001; i <= 2_00_0000; i++) {
            if (bf.contains(i)) {
                count++;
            }
        }
        System.out.println(count);
    }

    static void test2() {
        String[] urls = {};
        BloomFilter<String> bf = new BloomFilter<String>(10_0000_0000, 0.01);
		/*
		for (String url : urls) {
			if (bf.contains(url)) continue;
			// 爬这个url
			// ......

			// 放进BloomFilter中
			bf.put(url);
		}*/

        for (String url : urls) {
            if (bf.put(url) == false) continue;
            // 爬这个url
            // ......
        }
    }

    public static void main(String[] args) {

        test2();
    }
}