package com.base.common.util;

import jakarta.annotation.Resource;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Redis 操作工具类（基于 Spring Data Redis 原生 API，无第三方依赖）
 */
@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    // ==================== 通用操作 ====================
    /**
     * 判断 key 是否存在
     */
    public boolean hasKey(String key) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除指定 key（支持批量删除）
     */
    @SafeVarargs
    public final boolean delete(String... keys) {
        if (keys == null || keys.length == 0) {
            return false;
        }
        try {
            if (keys.length == 1) {
                return Boolean.TRUE.equals(redisTemplate.delete(keys[0]));
            } else {
                Long count = redisTemplate.delete(Arrays.asList(keys));
                return count != null && count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 设置 key 的过期时间
     * @param key 键
     * @param time 过期时间（单位：秒）
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取 key 的过期时间（单位：秒）
     * @return 过期时间（-1：永久有效；-2：key 不存在）
     */
    public long getExpire(String key) {
        try {
            Long expire = redisTemplate.getExpire(key, TimeUnit.SECONDS);
            return expire == null ? -2 : expire;
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
    }

    // ==================== 字符串（String）操作 ====================
    /**
     * 存储字符串类型数据
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存储字符串类型数据（带过期时间）
     * @param time 过期时间（单位：秒）
     */
    public boolean set(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取字符串类型数据
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        try {
            return (T) redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 字符串值自增（+1）
     * @return 自增后的值
     */
    public long incr(String key) {
        return incr(key, 1);
    }

    /**
     * 字符串值自增指定步长
     * @param delta 步长（正数自增，负数自减）
     * @return 自增后的值
     */
    public long incr(String key, long delta) {
        try {
            return redisTemplate.opsForValue().increment(key, delta);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ==================== 哈希（Hash）操作 ====================
    /**
     * 存储哈希类型数据（单个字段）
     */
    public boolean hSet(String key, String hashKey, Object value) {
        try {
            redisTemplate.opsForHash().put(key, hashKey, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存储哈希类型数据（单个字段，带过期时间）
     */
    public boolean hSet(String key, String hashKey, Object value, long time) {
        try {
            hSet(key, hashKey, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存储哈希类型数据（多个字段）
     */
    public boolean hMSet(String key, Map<String, Object> map) {
        try {
            if (!CollectionUtils.isEmpty(map)) {
                redisTemplate.opsForHash().putAll(key, map);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 存储哈希类型数据（多个字段，带过期时间）
     */
    public boolean hMSet(String key, Map<String, Object> map, long time) {
        try {
            boolean success = hMSet(key, map);
            if (success && time > 0) {
                expire(key, time);
            }
            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取哈希类型指定字段的值
     */
    @SuppressWarnings("unchecked")
    public <T> T hGet(String key, String hashKey) {
        try {
            return (T) redisTemplate.opsForHash().get(key, hashKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取哈希类型所有字段和值
     */
    public Map<Object, Object> hGetAll(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    /**
     * 删除哈希类型指定字段
     */
    public boolean hDelete(String key, Object... hashKeys) {
        try {
            if (hashKeys == null || hashKeys.length == 0) {
                return false;
            }
            Long count = redisTemplate.opsForHash().delete(key, hashKeys);
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // ==================== 列表（List）操作 ====================
    /**
     * 列表左侧添加元素（从头部插入）
     */
    public boolean lPush(String key, Object... values) {
        try {
            if (values == null || values.length == 0) {
                return false;
            }
            redisTemplate.opsForList().leftPushAll(key, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 列表右侧添加元素（从尾部插入）
     */
    public boolean rPush(String key, Object... values) {
        try {
            if (values == null || values.length == 0) {
                return false;
            }
            redisTemplate.opsForList().rightPushAll(key, values);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取列表指定区间的元素
     * @param start 起始索引（0 开始）
     * @param end 结束索引（-1 表示最后一个元素）
     */
    @SuppressWarnings("unchecked")
    public <T> List<T> lRange(String key, long start, long end) {
        try {
            return (List<T>) redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * 列表左侧弹出元素（从头部取出并删除）
     */
    @SuppressWarnings("unchecked")
    public <T> T lPop(String key) {
        try {
            return (T) redisTemplate.opsForList().leftPop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 列表右侧弹出元素（从尾部取出并删除）
     */
    @SuppressWarnings("unchecked")
    public <T> T rPop(String key) {
        try {
            return (T) redisTemplate.opsForList().rightPop(key);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取列表长度
     */
    public long lSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ==================== 集合（Set）操作 ====================
    /**
     * 集合添加元素（自动去重）
     */
    public boolean sAdd(String key, Object... values) {
        try {
            if (values == null || values.length == 0) {
                return false;
            }
            Long count = redisTemplate.opsForSet().add(key, values);
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取集合所有元素
     */
    @SuppressWarnings("unchecked")
    public <T> Set<T> sMembers(String key) {
        try {
            return (Set<T>) redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }

    /**
     * 判断元素是否在集合中
     */
    public boolean sIsMember(String key, Object value) {
        try {
            return Boolean.TRUE.equals(redisTemplate.opsForSet().isMember(key, value));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除集合指定元素
     */
    public boolean sRemove(String key, Object... values) {
        try {
            if (values == null || values.length == 0) {
                return false;
            }
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取集合大小
     */
    public long sSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ==================== 有序集合（ZSet）操作 ====================
    /**
     * 有序集合添加元素（带分数，用于排序）
     */
    public boolean zAdd(String key, Object value, double score) {
        try {
            redisTemplate.opsForZSet().add(key, value, score);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取有序集合指定分数区间的元素（升序）
     * @param min 最小分数
     * @param max 最大分数
     */
    @SuppressWarnings("unchecked")
    public <T> Set<T> zRangeByScore(String key, double min, double max) {
        try {
            return (Set<T>) redisTemplate.opsForZSet().rangeByScore(key, min, max);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptySet();
        }
    }

    /**
     * 获取有序集合元素的分数
     */
    public Double zScore(String key, Object value) {
        try {
            return redisTemplate.opsForZSet().score(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除有序集合指定元素
     */
    public boolean zRemove(String key, Object... values) {
        try {
            if (values == null || values.length == 0) {
                return false;
            }
            Long count = redisTemplate.opsForZSet().remove(key, values);
            return count != null && count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取有序集合大小
     */
    public long zSize(String key) {
        try {
            return redisTemplate.opsForZSet().size(key);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}