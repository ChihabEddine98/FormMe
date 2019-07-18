package track.utils

/**
 * Created by Ilias on 20/03/2017.
 */
class StatisticsUtil {

    ArrayList<Float> data
    int size

    StatisticsUtil(ArrayList<Float> data)
    {
        this.data = data
        size = data.size()
    }

    Float getMean()
    {
        Float mean = 0f
        if(size > 0)
        {
            Float sum = 0f
            for(Float a : data)
            {
                sum += a
            }
            mean = (sum / size)
        }
        return (mean)
    }

    Float getVariance()
    {
        Float mean = getMean()
        Float temp = 0f
        for(Float a : data)
        {
            temp += (a - mean) * (a - mean)
        }

        return (temp / size)
    }

    Float getStandardDeviation()
    {
        return (Float)Math.sqrt(getVariance())
    }

    Float getMedian()
    {
        Collections.sort(data)
        Float result
        int flag1 = (int)size / 2
        int flag2 = flag1 -1

        if(size % 2 == 0)
        {
            result = (data.get(flag1) + data.get(flag2)) / 2
        } else
        {
            result = data.get(flag1)
        }
        return result
    }
}
